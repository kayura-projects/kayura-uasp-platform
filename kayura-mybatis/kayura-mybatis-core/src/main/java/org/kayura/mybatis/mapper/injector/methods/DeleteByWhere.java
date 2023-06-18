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
