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

import org.kayura.mybatis.mapper.injector.AbstractMethod;
import org.kayura.mybatis.mapper.injector.SqlFragmentBuilder;
import org.kayura.mybatis.mapper.injector.SqlMethod;
import org.kayura.mybatis.mapper.injector.SqlMethods;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteByIds extends AbstractMethod {

  public static final SqlMethod PHYSICAL = new SqlMethod(
    SqlMethods.deleteByIds,
    "<script>DELETE FROM %s WHERE %s IN ( <foreach collection=\"" + ROW_IDS + "\" item=\"" + ROW_ID + "\" separator=\",\">#{" + ROW_ID + "}</foreach> )</script>");

  public static final SqlMethod LOGICAL = new SqlMethod(
    SqlMethods.deleteByIds,
    "<script>UPDATE %s SET %s WHERE %s IN ( <foreach collection=\"" + ROW_IDS + "\" item=\"" + ROW_ID + "\" separator=\",\">#{" + ROW_ID + "}</foreach> )</script>");

  @Override
  protected MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment) {

    EntityInfo entityInfo = sqlFragment.getEntityTable();

    // 逻辑删除用 Update，物理删除用 Delete
    if (entityInfo.isLogicDelete()) {
      String sql = LOGICAL.formatSql(entityInfo.getTableName(), sqlFragment.logicDeleteSet(false), entityInfo.getIdColumn());
      SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);
      return addMappedStatementByUpdate(mapperClass, entityInfo.getClass(), LOGICAL.method(), sqlSource);
    } else {
      String sql = PHYSICAL.formatSql(entityInfo.getTableName(), entityInfo.getIdColumn());
      SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);
      return addMappedStatementByDelete(mapperClass, PHYSICAL.method(), sqlSource);
    }
  }
}
