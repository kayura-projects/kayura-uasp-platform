/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
