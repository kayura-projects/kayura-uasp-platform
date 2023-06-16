/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.injector.methods;

import org.kayura.mybatis.annotation.mapper.IdGenTypes;
import org.kayura.mybatis.mapper.injector.AbstractMethod;
import org.kayura.mybatis.mapper.injector.SqlFragmentBuilder;
import org.kayura.mybatis.mapper.injector.SqlMethod;
import org.kayura.mybatis.mapper.injector.SqlMethods;
import org.kayura.mybatis.mapper.metadata.EntityHelper;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.toolkit.StringKit;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 向数据库中插入一条记录。
 */
public class InsertOne extends AbstractMethod {

  public static final SqlMethod SQL = new SqlMethod(
    SqlMethods.insertOne,
    "<script>INSERT INTO %s ( %s ) VALUES ( %s )</script>");

  @Override
  protected MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment) {

    EntityInfo entityInfo = sqlFragment.getEntityTable();

    KeyGenerator keyGenerator = new NoKeyGenerator();
    String idColumn = null;
    String idProperty = null;

    // 如果存在主键，就处理主键的生成规则
    if (StringKit.isNotBlank(entityInfo.getIdProperty())) {
      if (IdGenTypes.AUTO_INC.equals(entityInfo.getIdGenType())) {
        keyGenerator = new Jdbc3KeyGenerator();
        idColumn = entityInfo.getIdColumn();
        idProperty = entityInfo.getIdProperty();
      } else if (entityInfo.getKeySequence() != null) {
        keyGenerator = EntityHelper.keyGenerator(entityInfo, builderAssistant, SQL.method(), languageDriver);
        idColumn = entityInfo.getIdColumn();
        idProperty = entityInfo.getIdProperty();
      }
    }

    String sql = SQL.formatSql(entityInfo.getTableName(), sqlFragment.allInsertColumns(), sqlFragment.allInsertProperties());
    SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);

    return addMappedStatementByInsert(mapperClass, entityInfo.getClass(), SQL.method(), keyGenerator, sqlSource, idColumn, idProperty);
  }

}
