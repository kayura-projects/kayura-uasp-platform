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

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.mapper.wrapper.AbstractQueryWrapper;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.toolkit.Constants;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.scripting.LanguageDriver;

public abstract class AbstractMethod implements Constants {

  protected static final Log logger = LogFactory.getLog(AbstractMethod.class);

  protected MbConfiguration configuration;
  protected LanguageDriver languageDriver;
  protected MapperBuilderAssistant builderAssistant;

  public void inject(MapperBuilderAssistant assistant, Class<?> mapperClass, EntityInfo tableInfo) {

    this.configuration = (MbConfiguration) assistant.getConfiguration();
    this.builderAssistant = assistant;
    this.languageDriver = configuration.getDefaultScriptingLanguageInstance();

    injectStatement(mapperClass, tableInfo.getEntityType(), new SqlFragmentBuilder(tableInfo));
  }

  protected abstract MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment);

  protected MappedStatement addMappedStatementBySelect(Class<?> mapperClass, String methodName, SqlSource sqlSource, Class<?> resultType) {
    return addMappedStatement(mapperClass,
      methodName,
      sqlSource,
      SqlCommandType.SELECT,
      AbstractQueryWrapper.class,
      null,
      resultType,
      new NoKeyGenerator(),
      null,
      null);
  }

  protected MappedStatement addMappedStatementBySelect(Class<?> mapperClass, String methodName, SqlSource sqlSource, EntityInfo entityInfo) {
    return addMappedStatement(mapperClass,
      methodName,
      sqlSource,
      SqlCommandType.SELECT,
      AbstractQueryWrapper.class,
      entityInfo.getResultMap(),
      entityInfo.getEntityType(),
      new NoKeyGenerator(),
      null,
      null);
  }

  protected MappedStatement addMappedStatementByInsert(Class<?> mapperClass, Class<?> entityClass, String methodName,
                                                       KeyGenerator keyGenerator, SqlSource sqlSource, String idColumn, String idProperty) {
    return addMappedStatement(mapperClass,
      methodName,
      sqlSource,
      SqlCommandType.INSERT,
      entityClass,
      null,
      null,
      keyGenerator,
      idProperty,
      idColumn);
  }

  protected MappedStatement addMappedStatementByUpdate(Class<?> mapperClass, Class<?> entityClass, String methodName, SqlSource sqlSource) {
    return addMappedStatement(mapperClass,
      methodName,
      sqlSource,
      SqlCommandType.UPDATE,
      entityClass,
      null,
      null,
      new NoKeyGenerator(),
      null,
      null);
  }

  protected MappedStatement addMappedStatementByDelete(Class<?> mapperClass, String methodName, SqlSource sqlSource) {
    return addMappedStatement(mapperClass,
      methodName,
      sqlSource,
      SqlCommandType.DELETE,
      null,
      null,
      Integer.class,
      new NoKeyGenerator(),
      null,
      null);
  }

  protected MappedStatement addMappedStatement(Class<?> mapperClass, String methodName, SqlSource sqlSource,
                                               SqlCommandType sqlCommandType, Class<?> parameterType,
                                               String resultMap, Class<?> resultType, KeyGenerator keyGenerator,
                                               String keyProperty, String keyColumn) {

    String statementId = mapperClass.getName() + DOT + methodName;
    if (configuration.hasStatement(statementId, false)) {
      logger.warn(statementId + " 已经载入。");
      return null;
    }

    boolean isSelect = sqlCommandType == SqlCommandType.SELECT;
    return builderAssistant.addMappedStatement(
      methodName,
      sqlSource,
      StatementType.PREPARED,
      sqlCommandType,
      null,
      null,
      null,
      parameterType,
      resultMap,
      resultType,
      null,
      !isSelect,
      isSelect,
      false,
      keyGenerator,
      keyProperty,
      keyColumn,
      configuration.getDatabaseId(),
      languageDriver,
      null
    );

  }
}
