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

package org.kayura.mybatis.session;

import org.kayura.mybatis.binding.MbMapperRegistry;
import org.kayura.mybatis.executor.MbBatchExecutor;
import org.kayura.mybatis.executor.MbReuseExecutor;
import org.kayura.mybatis.executor.MbSimpleExecutor;
import org.kayura.mybatis.mapper.BaseMapper;
import org.kayura.mybatis.mapper.injector.DefaultMethodInjector;
import org.kayura.mybatis.mapper.injector.IKeyGenerator;
import org.kayura.mybatis.mapper.injector.IMethodInjector;
import org.kayura.mybatis.mapper.metadata.EntityInfo;
import org.kayura.mybatis.scripting.xmltags.MbXMLLanguageDriver;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.executor.CachingExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 用于扩展自定义的 Mybatis 中心配置对象
 *
 * @author LiangXia
 */
public class MbConfiguration extends Configuration {

  protected static final Log logger = LogFactory.getLog(MbConfiguration.class);

  protected Class<?> superMapperClass = BaseMapper.class;
  protected IKeyGenerator keyGenerator;
  protected final MbMapperRegistry mapperRegistry = new MbMapperRegistry(this);
  protected final Map<Class<?>, EntityInfo> entityInfos = new HashMap<>();

  protected Set<String> mapperRegistryCache = new ConcurrentSkipListSet<>();
  private IMethodInjector methodInjector = new DefaultMethodInjector();
  private SqlSessionFactory sqlSessionFactory;

  public MbConfiguration() {
    super();
    this.mapUnderscoreToCamelCase = true;
  }

  public MbConfiguration(Environment environment) {
    super(environment);
  }

  public void signGlobalConfig(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  public IKeyGenerator getKeyGenerator() {
    return keyGenerator;
  }

  public void setKeyGenerator(IKeyGenerator keyGenerator) {
    this.keyGenerator = keyGenerator;
  }

  @Override
  public MapperRegistry getMapperRegistry() {
    return mapperRegistry;
  }

  @Override
  public <T> void addMapper(Class<T> type) {
    mapperRegistry.addMapper(type);
  }

  @Override
  public void addMappers(String packageName) {
    mapperRegistry.addMappers(packageName);
  }

  @Override
  public void addMappers(String packageName, Class<?> superType) {
    mapperRegistry.addMappers(packageName, superType);
  }

  @Override
  public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
    return mapperRegistry.getMapper(type, sqlSession);
  }

  @Override
  public boolean hasMapper(Class<?> type) {
    return mapperRegistry.hasMapper(type);
  }

  public Set<String> getMapperRegistryCache() {
    return mapperRegistryCache;
  }

  public boolean hasMapperRegistryCache(String mapperName) {
    return mapperRegistryCache.contains(mapperName);
  }

  public void addMapperRegistryCache(String mapperName) {
    mapperRegistryCache.add(mapperName);
  }

  @Override
  public void setDefaultScriptingLanguage(Class<? extends LanguageDriver> driver) {
    if (driver == null) {
      driver = MbXMLLanguageDriver.class;
    }
    getLanguageRegistry().setDefaultDriverClass(driver);
  }

  @Override
  public Executor newExecutor(Transaction transaction, ExecutorType executorType) {
    executorType = executorType == null ? defaultExecutorType : executorType;
    executorType = executorType == null ? ExecutorType.SIMPLE : executorType;
    Executor executor;
    if (ExecutorType.BATCH == executorType) {
      executor = new MbBatchExecutor(this, transaction);
    } else if (ExecutorType.REUSE == executorType) {
      executor = new MbReuseExecutor(this, transaction);
    } else {
      executor = new MbSimpleExecutor(this, transaction);
    }
    if (cacheEnabled) {
      executor = new CachingExecutor(executor);
    }
    executor = (Executor) interceptorChain.pluginAll(executor);
    return executor;
  }

  public boolean isSupperMapperClass(Class<?> mapperClass) {
    return this.superMapperClass.isAssignableFrom(mapperClass);
  }

  public void setSuperMapperClass(Class<?> superMapperClass) {
    this.superMapperClass = superMapperClass;
  }

  public boolean hasEntityInfo(Class<?> entityClass) {
    return entityInfos.containsKey(entityClass);
  }

  public void addEntityInfo(EntityInfo entityInfo) {
    entityInfos.put(entityInfo.getEntityType(), entityInfo);
  }

  public EntityInfo getEntityInfo(Class<?> entityClass) {
    return entityInfos.get(entityClass);
  }

  public void inspectInject(MapperBuilderAssistant assistant, Class<?> type) {
    methodInjector.inject(assistant, type);
  }

  public void setMethodInjector(IMethodInjector methodInjector) {
    this.methodInjector = methodInjector;
  }

  public SqlSessionFactory getSqlSessionFactory() {
    return sqlSessionFactory;
  }
}
