/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.metadata;

import org.kayura.except.ExceptUtils;
import org.kayura.mybatis.annotation.mapper.Column;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.mapper.injector.IKeyGenerator;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.toolkit.ReflectKit;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.LanguageDriver;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class EntityHelper {

  static final Map<Class<?>, EntityInfo> ENTITY_CACHE = new ConcurrentHashMap<>();
  static final Map<Class<?>, String> ID_CACHE = new ConcurrentHashMap<>();

  public static EntityInfo getEntityInfo(Class<?> clazz) {

    if (clazz == null
      || ReflectKit.isPrimitiveOrWrapper(clazz)
      || clazz.equals(String.class)) {
      return null;
    }

    EntityInfo entityInfo = ENTITY_CACHE.get(clazz);
    if (entityInfo != null) {
      return entityInfo;
    } else {
      Class<?> classItem = clazz;
      while (!Object.class.equals(classItem)) {
        classItem = classItem.getSuperclass();
        entityInfo = ENTITY_CACHE.get(classItem);
        if (entityInfo != null) {
          return entityInfo;
        }
      }
    }

    return null;
  }

  public static EntityInfo resolve(MbConfiguration configuration, String currentNamespace, Class<?> entityClazz) {

    EntityInfo entityInfo = null;
    if (!ENTITY_CACHE.containsKey(entityClazz)) {
      EntityResolver resolver = new EntityResolver(configuration, entityClazz);
      entityInfo = resolver.resolve(currentNamespace);
      ENTITY_CACHE.put(entityClazz, entityInfo);
    }
    return entityInfo;
  }

  public static KeyGenerator keyGenerator(EntityInfo entityInfo, MapperBuilderAssistant assistant,
                                          String baseStatementId, LanguageDriver languageDriver) {
    MbConfiguration configuration = (MbConfiguration) assistant.getConfiguration();
    IKeyGenerator keyGenerator = configuration.getKeyGenerator();
    if (null == keyGenerator) {
      throw new IllegalArgumentException("没有配置 IKeyGenerator 的实现类。");
    }

    String id = baseStatementId + SelectKeyGenerator.SELECT_KEY_SUFFIX;
    Class<?> resultTypeClass = entityInfo.getKeySequence().clazz();
    StatementType statementType = StatementType.PREPARED;
    String idProperty = entityInfo.getIdProperty();
    String idColumn = entityInfo.getIdColumn();

    SqlSource sqlSource = languageDriver.createSqlSource(configuration,
      keyGenerator.executeSql(entityInfo.getKeySequence().value()), null);
    assistant.addMappedStatement(id, sqlSource, statementType, SqlCommandType.SELECT,
      null, null, null, null, null,
      resultTypeClass, null, false, false, false,
      new NoKeyGenerator(), idProperty, idColumn, null, languageDriver, null);

    id = assistant.applyCurrentNamespace(id, false);
    MappedStatement keyStatement = configuration.getMappedStatement(id, false);
    SelectKeyGenerator selectKeyGenerator = new SelectKeyGenerator(keyStatement, true);
    configuration.addKeyGenerator(id, selectKeyGenerator);

    return selectKeyGenerator;
  }

  public static String resolveIdColumn(Class<?> entityClazz) {

    String idColumn = null;
    if (!ID_CACHE.containsKey(entityClazz)) {
      List<Field> idFields = ReflectKit.getEntityFields(entityClazz).stream()
        .filter(a -> a.isAnnotationPresent(Id.class)).toList();
      if (idFields.isEmpty()) {
        ExceptUtils.config("目标实体未定义主键ID。");
      } else if (idFields.size() > 1) {
        ExceptUtils.config("目标实体存在多个主键ID。");
      } else {
        Column column = idFields.get(0).getAnnotation(Column.class);
        if (column != null && StringKit.isNotBlank(column.value())) {
          idColumn = column.value();
        } else {
          idColumn = StringKit.camelToColumn(idFields.get(0).getName());
        }
        ID_CACHE.put(entityClazz, idColumn);
      }
    } else {
      idColumn = ID_CACHE.get(entityClazz);
    }
    return idColumn;
  }
}
