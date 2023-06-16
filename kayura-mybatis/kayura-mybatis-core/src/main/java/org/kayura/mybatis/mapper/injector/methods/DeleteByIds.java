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
