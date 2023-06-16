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

import org.kayura.mybatis.annotation.mapper.SelectModes;
import org.kayura.mybatis.mapper.injector.AbstractMethod;
import org.kayura.mybatis.mapper.injector.SqlFragmentBuilder;
import org.kayura.mybatis.mapper.injector.SqlMethod;
import org.kayura.mybatis.mapper.injector.SqlMethods;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

/**
 * 根据主键 ID 获取一条记录。
 */
public class SelectById extends AbstractMethod {

  private static final SqlMethod SQL = new SqlMethod(
    SqlMethods.selectById,
    "<script>SELECT %s FROM %s WHERE %s.%s = #{" + ROW_ID + "}</script>");

  @Override
  protected MappedStatement injectStatement(Class<?> mapperClass, Class<?> entityClass, SqlFragmentBuilder sqlFragment) {

    EntityInfo entityInfo = sqlFragment.getEntityTable();

    String sql = SQL.formatSql(
      sqlFragment.allSelectColumns(SelectModes.SINGLE, false),
      sqlFragment.allFromTable(),
      entityInfo.getTableAlias(),
      entityInfo.getIdColumn());

    SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, entityClass);
    return addMappedStatementBySelect(mapperClass, SQL.method(), sqlSource, entityInfo);
  }

}
