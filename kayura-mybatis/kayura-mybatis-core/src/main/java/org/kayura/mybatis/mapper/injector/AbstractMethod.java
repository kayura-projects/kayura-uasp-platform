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
