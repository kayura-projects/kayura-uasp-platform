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

public class DeleteByWhere extends AbstractMethod {

  public static final SqlMethod LOGICAL = new SqlMethod(
    SqlMethods.deleteByWhere,
    "<script>UPDATE %s SET %s %s</script>");

  public static final SqlMethod PHYSICAL = new SqlMethod(
    SqlMethods.deleteByWhere,
    "<script>DELETE %s FROM %s %s</script>");

  @Override
  protected MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment) {

    EntityInfo entityInfo = sqlFragment.getEntityTable();

    // 逻辑删除用 Update，物理删除用 Delete
    if (entityInfo.isLogicDelete()) {
      String sql = LOGICAL.formatSql(sqlFragment.allFromTable(), sqlFragment.logicDeleteSet(true),
        sqlFragment.allQueryWrapper(false, false, false));
      SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);
      return addMappedStatementByUpdate(mapperClass, entityInfo.getClass(), LOGICAL.method(), sqlSource);
    } else {
      String sql = PHYSICAL.formatSql(entityInfo.getTableAlias(), sqlFragment.allFromTable(),
        sqlFragment.allQueryWrapper(false, false, false));
      SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);
      return addMappedStatementByDelete(mapperClass, PHYSICAL.method(), sqlSource);
    }
  }
}
