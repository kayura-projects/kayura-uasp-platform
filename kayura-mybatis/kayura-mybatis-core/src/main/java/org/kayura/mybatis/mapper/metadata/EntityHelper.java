/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
        .filter(a -> a.isAnnotationPresent(Id.class)).collect(Collectors.toList());
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
