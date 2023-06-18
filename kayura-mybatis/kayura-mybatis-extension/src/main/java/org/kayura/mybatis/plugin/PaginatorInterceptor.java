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

package org.kayura.mybatis.plugin;

import org.kayura.mybatis.plugin.paginator.Dialect;
import org.kayura.mybatis.plugin.paginator.SqlHelper;
import org.kayura.mybatis.plugin.paginator.dialect.MySQLDialect;
import org.kayura.mybatis.plugin.paginator.dialect.OracleDialect;
import org.kayura.mybatis.plugin.paginator.dialect.SQLServer2005Dialect;
import org.kayura.mybatis.toolkit.PropertiesKit;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.mybatis.type.PageWrapper;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * @author 夏亮（liangxia@live.com）
 */
@Intercepts(value = {
  @Signature(
    type = Executor.class,
    method = "query",
    args = {
      MappedStatement.class,
      Object.class,
      RowBounds.class,
      ResultHandler.class
    }
  )
})
public class PaginatorInterceptor implements Interceptor {

  private static final Log logger = LogFactory.getLog(PaginatorInterceptor.class);

  static int MAPPED_STATEMENT_INDEX = 0;
  static int PARAMETER_INDEX = 1;
  static int ROW_BOUNDS_INDEX = 2;
  //static int RESULT_HANDLER_INDEX = 3;

  static Map<String, String> dialectAlias = new HashMap<>();
  static ExecutorService Pool;
  String dialectClass = MySQLDialect.class.getName();
  boolean asyncTotalCount = false;

  static {
    dialectAlias.put("mysql", MySQLDialect.class.getName());
    dialectAlias.put("sqlserver", SQLServer2005Dialect.class.getName());
    dialectAlias.put("oracle", OracleDialect.class.getName());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public Object intercept(final Invocation invocation) throws Throwable {

    final Object[] queryArgs = invocation.getArgs();
    final RowBounds rowBounds = (RowBounds) queryArgs[ROW_BOUNDS_INDEX];

    if (!(rowBounds instanceof PageBounds)) {
      return invocation.proceed();
    }

    final Executor executor = (Executor) invocation.getTarget();
    final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
    final Object parameter = queryArgs[PARAMETER_INDEX];
    final PageBounds pageBounds = (PageBounds) rowBounds;

    if (pageBounds.getOffset() == RowBounds.NO_ROW_OFFSET &&
      pageBounds.getLimit() == RowBounds.NO_ROW_LIMIT) {
      return invocation.proceed();
    }

    final Dialect dialect;
    try {
      Class<?> clazz = Class.forName(dialectClass);
      Constructor<?> constructor = clazz.getConstructor(MappedStatement.class, Object.class, PageBounds.class);
      dialect = (Dialect) constructor.newInstance(new Object[]{ms, parameter, pageBounds});
    } catch (Exception e) {
      throw new ClassNotFoundException("Cannot create dialect instance: " + dialectClass, e);
    }

    final BoundSql boundSql = ms.getBoundSql(parameter);

    queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, dialect.getPageSQL(), dialect.getParameterMappings(), dialect.getParameterObject());
    queryArgs[PARAMETER_INDEX] = dialect.getParameterObject();
    queryArgs[ROW_BOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);

    Future<List> listFuture = call((Callable<List>) () -> (List) invocation.proceed(), asyncTotalCount);
    Future<Integer> countFuture = call((Callable<Integer>) () -> SqlHelper.getCount(ms, parameter, boundSql, dialect), asyncTotalCount);

    return new PageWrapper(listFuture.get(), countFuture.get());
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private <T> Future<T> call(Callable callable, boolean async) {
    if (async) {
      return Pool.submit(callable);
    } else {
      FutureTask<T> future = new FutureTask<T>(callable);
      future.run();
      return future;
    }
  }

  private MappedStatement copyFromNewSql(
    MappedStatement ms, BoundSql boundSql, String sql, List<ParameterMapping> parameterMappings,
    Object parameter) {

    BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql, parameterMappings, parameter);
    return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
  }

  private BoundSql copyFromBoundSql(
    MappedStatement ms, BoundSql boundSql, String sql, List<ParameterMapping> parameterMappings,
    Object parameter) {

    BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, parameterMappings, parameter);
    for (ParameterMapping mapping : boundSql.getParameterMappings()) {
      String prop = mapping.getProperty();
      if (boundSql.hasAdditionalParameter(prop)) {
        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
      }
    }
    return newBoundSql;
  }

  private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {

    Builder builder = new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

    builder.resource(ms.getResource());
    builder.fetchSize(ms.getFetchSize());
    builder.statementType(ms.getStatementType());
    builder.keyGenerator(ms.getKeyGenerator());

    if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
      StringBuilder keyProperties = new StringBuilder();
      for (String keyProperty : ms.getKeyProperties()) {
        keyProperties.append(keyProperty).append(",");
      }
      keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
      builder.keyProperty(keyProperties.toString());
    }

    builder.timeout(ms.getTimeout());
    builder.parameterMap(ms.getParameterMap());
    builder.resultMaps(ms.getResultMaps());
    builder.resultSetType(ms.getResultSetType());
    builder.cache(ms.getCache());
    builder.flushCacheRequired(ms.isFlushCacheRequired());
    builder.useCache(ms.isUseCache());

    return builder.build();
  }

  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  public void setProperties(Properties properties) {

    PropertiesKit propertiesHelper = new PropertiesKit(properties);
    String dialect = propertiesHelper.getRequiredString("dialect").toLowerCase();
    if (dialectAlias.containsKey(dialect)) {
      dialect = dialectAlias.get(dialect);
    }
    setDialectClass(dialect);
    setAsyncTotalCount(propertiesHelper.getBoolean("asyncTotalCount", false));
    setPoolMaxSize(propertiesHelper.getInt("poolMaxSize", 0));
  }

  public static class BoundSqlSqlSource implements SqlSource {

    BoundSql boundSql;

    public BoundSqlSqlSource(BoundSql boundSql) {
      this.boundSql = boundSql;
    }

    public BoundSql getBoundSql(Object parameterObject) {
      return boundSql;
    }
  }

  public void setDialectClass(String dialectClass) {
    this.dialectClass = dialectClass;
  }

  public void setAsyncTotalCount(boolean asyncTotalCount) {
    this.asyncTotalCount = asyncTotalCount;
  }

  public void setPoolMaxSize(int poolMaxSize) {
    if (poolMaxSize > 0) {
      logger.debug("poolMaxSize: " + poolMaxSize);
      Pool = Executors.newFixedThreadPool(poolMaxSize);
    } else {
      Pool = Executors.newCachedThreadPool();
    }
  }
}
