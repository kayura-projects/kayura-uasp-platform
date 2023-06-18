/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.metadata;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.toolkit.ReflectKit;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.builder.BuilderException;
import org.joda.time.DateTime;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EntityResolver {

  private static final List<Class<?>> ALLOW_CREATE_TYPES = new ArrayList<>();

  static {
    ALLOW_CREATE_TYPES.add(LocalDateTime.class);
    ALLOW_CREATE_TYPES.add(Date.class);
    ALLOW_CREATE_TYPES.add(DateTime.class);
  }

  protected final MbConfiguration configuration;
  protected final Class<?> entityClazz;
  protected final List<Field> entityFields;

  public EntityResolver(MbConfiguration configuration, Class<?> entityClazz) {
    this.configuration = configuration;
    this.entityClazz = entityClazz;
    this.entityFields = ReflectKit.getEntityFields(entityClazz);
  }

  public EntityInfo resolve(String currentNamespace) {

    EntityInfo info = configuration.getEntityInfo(entityClazz);
    if (info == null) {
      info = new EntityInfo(configuration, entityClazz);
      info.setCurrentNamespace(currentNamespace);
      configuration.addEntityInfo(info);

      resolveTable(info);
      resolveLogicDelete(info);
      resolveIdColumn(info);
      resolveFkColumn(info);
      resolveSortColumn(info);
      resolveBaseColumn(info);
    }

    return info;
  }

  private void resolveTable(EntityInfo info) {

    Table table = entityClazz.getAnnotation(Table.class);
    if (table == null) {
      throw new BuilderException(entityClazz.getName() + " 必须提供 @Table 注解。");
    }

    if (StringKit.isBlank(table.value())) {
      info.setTableName(StringKit.classToTable(entityClazz.getSimpleName()));
    } else {
      info.setTableName(table.value());
    }

    if (StringKit.isNotBlank(table.alias())) {
      info.setTableAlias(table.alias());
    } else {
      info.setTableAlias("mt");
    }

    if (StringKit.isNotBlank(table.resultMap())) {
      info.setResultMap(table.resultMap());
    }

    info.setSchema(table.schema());
    info.setLogicDelete(table.logicDelete());
  }

  private void resolveFkColumn(EntityInfo info) {

    List<Field> fkFields = entityFields.stream()
      .filter(a -> a.isAnnotationPresent(ForeignKey.class) || a.isAnnotationPresent(ForeignKeyList.class))
      .collect(Collectors.toList());
    for (Field fkField : fkFields) {

      ForeignKey[] fkList = fkField.getAnnotationsByType(ForeignKey.class);
      for (ForeignKey fk : fkList) {

        JoinTable joinTable = new JoinTable();
        if (StringKit.isNotBlank(fk.table())) {
          joinTable.setTable(fk.table());
          if (StringKit.isNotBlank(fk.pkName())) {
            joinTable.setPkName(fk.pkName());
          } else {
            joinTable.setPkName(StringKit.camelToColumn(fkField.getName()));
          }
        } else if (!fk.entity().equals(Object.class)) {
          Class<?> targetClass = fk.entity();
          Table table = targetClass.getAnnotation(Table.class);
          if (table != null) {
            joinTable.setTable(table.value());
          }
          if (StringKit.isBlank(fk.pkName())) {
            String idColumn = EntityHelper.resolveIdColumn(targetClass);
            joinTable.setPkName(idColumn);
          } else {
            joinTable.setPkName(fk.pkName());
          }
        }
        joinTable.setAlias(fk.alias());
        joinTable.setJoinType(fk.joinType());
        joinTable.setCondition(fk.condition());

        RefColumn refColumn = fkField.getAnnotation(RefColumn.class);
        if (refColumn != null) {
          joinTable.setFrom(refColumn.from());
          if (StringKit.isNotBlank(refColumn.value())) {
            joinTable.setFkName(refColumn.value());
          }
        } else {
          joinTable.setFrom(info.getTableAlias());
          Column column = fkField.getAnnotation(Column.class);
          if (column != null && StringKit.isNotBlank(column.value())) {
            joinTable.setFkName(column.value());
          }
        }
        if (StringKit.isBlank(joinTable.getFkName())) {
          joinTable.setFkName(StringKit.camelToColumn(fkField.getName()));
        }

        info.addJoinTable(joinTable);
      }
    }
  }

  private void resolveLogicDelete(EntityInfo info) {

    if (info.isLogicDelete()) {
      Field deleteField = entityFields.stream()
        .filter(a -> a.isAnnotationPresent(DeleteAt.class))
        .findFirst()
        .orElse(null);
      if (deleteField != null) {
        DeleteAt deleteAt = deleteField.getAnnotation(DeleteAt.class);
        info.setDeleteColumn(StringKit.camelToColumn(deleteField.getName()));
        info.setDeletedValue(deleteAt.value());
        info.setDeleteType(deleteField.getType());
      } else {
        throw new BuilderException("已经对实体 " + info.getEntityType().getSimpleName() + " 标记了逻辑删除，但没有找到标记 @DeleteAt 的字段。");
      }
    }
  }

  private void resolveSortColumn(EntityInfo info) {

    List<Field> sortFields = entityFields.stream()
      .filter(a -> a.isAnnotationPresent(Sort.class))
      .collect(Collectors.toList());
    for (Field sortField : sortFields) {

      Sorting sorting = Sorting.builder();
      sorting.setColumnName(StringKit.camelToColumn(sortField.getName()));

      Sort sort = sortField.getAnnotation(Sort.class);
      sorting.setSortIndex(sort.value());
      sorting.setSortDesc(sort.desc());

      String owner = info.getTableAlias();
      Column column = sortField.getAnnotation(Column.class);
      if (column != null) {
        if (StringKit.isNotBlank(column.value())) {
          sorting.setColumnName(column.value());
        }
        sorting.setColumnAlias(column.alias());
        if (StringKit.isNotBlank(column.owner())) {
          owner = column.owner();
        }
      } else {
        RefColumn refColumn = sortField.getAnnotation(RefColumn.class);
        if (refColumn != null) {
          if (StringKit.isNotBlank(refColumn.value())) {
            sorting.setColumnName(refColumn.value());
          } else {
            sorting.setColumnName(StringKit.camelToColumn(refColumn.value()));
          }
          sorting.setColumnAlias(refColumn.alias());
          if (StringKit.isNotBlank(refColumn.from())) {
            owner = refColumn.from();
          }
        }
      }
      sorting.setOwner(owner);
      info.addSort(sorting);
    }
  }

  private void resolveIdColumn(EntityInfo info) {

    Field idField = entityFields.stream()
      .filter(a -> a.isAnnotationPresent(Id.class))
      .findFirst()
      .orElse(null);
    if (idField != null) {
      Column column = idField.getAnnotation(Column.class);
      if (column != null && StringKit.isNotBlank(column.value())) {
        info.setIdColumn(column.value());
      } else {
        info.setIdColumn(StringKit.camelToColumn(idField.getName()));
      }
      info.setIdProperty(idField.getName());
      info.setIdType(idField.getType());
      IdGen idGen = idField.getAnnotation(IdGen.class);
      if (idGen != null) {
        info.setIdGenType(idGen.value());
      } else {
        if (idField.getType().equals(Long.class)) {
          info.setIdGenType(IdGenTypes.SNOWFLAKE);
        } else if (idField.getType().equals(String.class)) {
          info.setIdGenType(IdGenTypes.SNOWFLAKE);
        } else if (idField.getType().equals(Integer.class)) {
          info.setIdGenType(IdGenTypes.AUTO_INC);
        } else {
          info.setIdGenType(IdGenTypes.INPUT);
        }
      }
    }
  }

  private void resolveBaseColumn(EntityInfo info) {

    for (Field field : entityFields) {

      // Id, Delete 为特殊字段
      if (field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(DeleteAt.class)) {
        continue;
      }

      ColumnInfo ci = new ColumnInfo();
      info.addColumn(ci);

      String propertyName = field.getName();
      String columnName = StringKit.camelToColumn(propertyName);

      Column column = field.getAnnotation(Column.class);
      if (column != null && StringKit.isNotBlank(column.value())) {
        columnName = column.value();
      }
      ci.setPropertyName(propertyName);
      ci.setJavaType(field.getType());
      ci.setVirtual(field.isAnnotationPresent(Virtual.class));
      if (column != null) {
        if (StringKit.isNotBlank(column.alias())) {
          ci.setColumnAlias(column.alias());
        }
        ci.setOwner(column.owner());
        ci.setDisplayName(column.displayName());
        ci.setHint(column.hint());
        ci.setEl(column.el());
        ci.setUnique(column.unique());
        ci.setNullable(column.nullable());
        ci.setMin(column.min());
        ci.setMax(column.max());
        ci.setZero(column.zero());
        ci.setJdbcType(column.jdbcType());
        ci.setTypeHandler(column.typeHandler());
        ci.setSelect(ci.isVirtual() ? SelectModes.NONE : column.select());
        ci.setInsert(!ci.isVirtual() && column.insert());
        ci.setUpdate(!ci.isVirtual() && column.update());
      } else {
        RefColumn refColumn = field.getAnnotation(RefColumn.class);
        if (refColumn != null) {
          if (StringKit.isNotBlank(refColumn.value())) {
            columnName = refColumn.value();
          }
          if (StringKit.isNotBlank(refColumn.alias())) {
            ci.setColumnAlias(refColumn.alias());
          }
          ci.setOwner(refColumn.from());
          ci.setDisplayName(refColumn.displayName());
          ci.setHint(refColumn.hint());
          ci.setJdbcType(refColumn.jdbcType());
          ci.setTypeHandler(refColumn.typeHandler());
          ci.setSelect(refColumn.select());
          ci.setInsert(Boolean.FALSE);
          ci.setUpdate(Boolean.FALSE);
        } else {
          ci.setInsert(!ci.isVirtual());
          ci.setUpdate(!ci.isVirtual());
        }
      }
      ci.setColumnName(columnName);

      // 如果没有设置列的别名，将使用属性名为别名
      if (StringKit.isBlank(ci.getColumnAlias())) {
        ci.setColumnAlias(StringKit.camelToColumn(propertyName));
      }

      // 没有设置 owner 均认为是主表别名
      if (StringKit.isBlank(ci.getOwner())) {
        ci.setOwner(info.getTableAlias());
      }

      // 设置显示名
      if (StringKit.isBlank(ci.getDisplayName())) {
        DisplayName displayName = field.getAnnotation(DisplayName.class);
        if (displayName != null) {
          ci.setDisplayName(displayName.value());
        }
      }

      Unique unique = field.getAnnotation(Unique.class);
      if (unique != null) {
        ci.setUnique(Boolean.TRUE);
      }

      ParentAt parentAt = field.getAnnotation(ParentAt.class);
      VersionAt versionAt = field.getAnnotation(VersionAt.class);
      CreateTimeAt createTimeAt = field.getAnnotation(CreateTimeAt.class);
      UpdateTimeAt updateTimeAt = field.getAnnotation(UpdateTimeAt.class);
      if (parentAt != null) {
        ci.setFeature(Features.PARENT);
      } else if (versionAt != null) {
        if (!Integer.class.equals(field.getType()) && !Long.class.equals(field.getType())) {
          throw new BuilderException("@Version 注解只能定义到 Integer,Long 类型字段。");
        }
        ci.setSelect(SelectModes.ALL);
        ci.setFeature(Features.VERSION);
        ci.setInsert(Boolean.TRUE);
        ci.setUpdate(Boolean.TRUE);
      } else if (createTimeAt != null) {
        if (!ALLOW_CREATE_TYPES.contains(field.getType())) {
          throw new BuilderException("@CreateTimeAt 注解只能定义到 LocalDateTime,Date,DateTime 类型字段。");
        }
        ci.setSelect(SelectModes.ALL);
        ci.setFeature(Features.CREATE_TIME);
        ci.setInsert(Boolean.TRUE);
        ci.setUpdate(Boolean.FALSE);
      } else if (updateTimeAt != null) {
        if (!ALLOW_CREATE_TYPES.contains(field.getType())) {
          throw new BuilderException("@UpdateTimeAt 注解只能定义到 LocalDateTime,Date,DateTime 类型字段。");
        }
        ci.setSelect(SelectModes.ALL);
        ci.setFeature(Features.UPDATED_TIME);
        ci.setInsert(Boolean.FALSE);
        ci.setUpdate(Boolean.TRUE);
      }

    }
  }

}
