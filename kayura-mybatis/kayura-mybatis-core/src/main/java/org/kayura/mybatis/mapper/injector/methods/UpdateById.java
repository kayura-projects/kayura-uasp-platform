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
