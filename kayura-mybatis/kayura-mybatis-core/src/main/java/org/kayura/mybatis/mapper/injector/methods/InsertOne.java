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
