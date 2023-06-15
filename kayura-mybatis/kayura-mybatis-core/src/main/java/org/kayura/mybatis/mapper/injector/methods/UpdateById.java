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
import org.kayura.mybatis.toolkit.Constants;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class UpdateById extends AbstractMethod {

  public static final SqlMethod SQL = new SqlMethod(
    SqlMethods.updateById,
    "<script>UPDATE %s <set> %s </set> WHERE %s = #{%s}</script>");

  @Override
  protected MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment) {

    EntityInfo entityInfo = sqlFragment.getEntityTable();

    String sql = SQL.formatSql(
      entityInfo.getTableName(),
      sqlFragment.allSetColumns(false, Constants.ENTITY),
      entityInfo.getIdColumn(),
      Constants.ENTITY_DOT + entityInfo.getIdProperty()
    );
    SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);

    return addMappedStatementByUpdate(mapperClass, entityInfo.getClass(), SQL.method(), sqlSource);
  }

}
