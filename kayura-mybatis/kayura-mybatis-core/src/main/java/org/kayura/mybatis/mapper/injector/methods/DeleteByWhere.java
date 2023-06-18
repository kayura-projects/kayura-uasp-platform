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
