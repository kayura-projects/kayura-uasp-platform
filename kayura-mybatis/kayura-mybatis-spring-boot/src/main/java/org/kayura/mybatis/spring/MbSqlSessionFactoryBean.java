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

package org.kayura.mybatis.spring;

import org.kayura.mybatis.builder.xml.MbXMLConfigBuilder;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.session.MbSqlSessionFactory;
import org.kayura.mybatis.session.MbSqlSessionFactoryBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.TypeHandler;
import org.jetbrains.annotations.NotNull;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.ClassUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;

import static org.springframework.util.Assert.notNull;
import static org.springframework.util.Assert.state;
import static org.springframework.util.ObjectUtils.isEmpty;
import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.tokenizeToStringArray;

public class MbSqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean, ApplicationListener<ApplicationEvent> {

  private static final Logger LOGGER = LoggerFactory.getLogger(MbSqlSessionFactoryBean.class);

  private static final ResourcePatternResolver RESOURCE_PATTERN_RESOLVER = new PathMatchingResourcePatternResolver();
  private static final MetadataReaderFactory METADATA_READER_FACTORY = new CachingMetadataReaderFactory();

  private Resource configLocation;
  private MbConfiguration configuration;
  private Resource[] mapperLocations;
  private DataSource dataSource;
  private TransactionFactory transactionFactory;
  private Properties configurationProperties;
  private MbSqlSessionFactoryBuilder sqlSessionFactoryBuilder = new MbSqlSessionFactoryBuilder();
  private MbSqlSessionFactory sqlSessionFactory;

  // EnvironmentAware requires spring 3.1
  private String environment = MbSqlSessionFactoryBean.class.getSimpleName();
  private boolean failFast;
  private Interceptor[] plugins;
  private TypeHandler<?>[] typeHandlers;
  private String typeHandlersPackage;
  private Class<?>[] typeAliases;
  private String typeAliasesPackage;
  private Class<?> typeAliasesSuperType;
  private LanguageDriver[] scriptingLanguageDrivers;
  private Class<? extends LanguageDriver> defaultScriptingLanguageDriver;

  // issue #19. No default provider.
  private DatabaseIdProvider databaseIdProvider;
  private Class<? extends VFS> vfs;
  private Cache cache;
  private ObjectFactory objectFactory;
  private ObjectWrapperFactory objectWrapperFactory;

  public void setObjectFactory(ObjectFactory objectFactory) {
    this.objectFactory = objectFactory;
  }

  public void setObjectWrapperFactory(ObjectWrapperFactory objectWrapperFactory) {
    this.objectWrapperFactory = objectWrapperFactory;
  }

  public DatabaseIdProvider getDatabaseIdProvider() {
    return databaseIdProvider;
  }

  public void setDatabaseIdProvider(DatabaseIdProvider databaseIdProvider) {
    this.databaseIdProvider = databaseIdProvider;
  }

  public Class<? extends VFS> getVfs() {
    return this.vfs;
  }

  public void setVfs(Class<? extends VFS> vfs) {
    this.vfs = vfs;
  }

  public Cache getCache() {
    return this.cache;
  }

  public void setCache(Cache cache) {
    this.cache = cache;
  }

  public void setPlugins(Interceptor... plugins) {
    this.plugins = plugins;
  }

  public void setTypeAliasesPackage(String typeAliasesPackage) {
    this.typeAliasesPackage = typeAliasesPackage;
  }

  public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
    this.typeAliasesSuperType = typeAliasesSuperType;
  }

  public void setTypeHandlersPackage(String typeHandlersPackage) {
    this.typeHandlersPackage = typeHandlersPackage;
  }

  public void setTypeHandlers(TypeHandler<?>... typeHandlers) {
    this.typeHandlers = typeHandlers;
  }

  public void setTypeAliases(Class<?>... typeAliases) {
    this.typeAliases = typeAliases;
  }

  public void setFailFast(boolean failFast) {
    this.failFast = failFast;
  }

  public void setConfigLocation(Resource configLocation) {
    this.configLocation = configLocation;
  }

  public void setConfiguration(MbConfiguration configuration) {
    this.configuration = configuration;
  }

  public void setMapperLocations(Resource... mapperLocations) {
    this.mapperLocations = mapperLocations;
  }

  public void setConfigurationProperties(Properties sqlSessionFactoryProperties) {
    this.configurationProperties = sqlSessionFactoryProperties;
  }

  public void setDataSource(DataSource dataSource) {
    if (dataSource instanceof TransactionAwareDataSourceProxy) {
      // If we got a TransactionAwareDataSourceProxy, we need to perform
      // transactions for its underlying target DataSource, else data
      // access code won't see properly exposed transactions (i.e.
      // transactions for the target DataSource).
      this.dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
    } else {
      this.dataSource = dataSource;
    }
  }

  public void setSqlSessionFactoryBuilder(MbSqlSessionFactoryBuilder sqlSessionFactoryBuilder) {
    this.sqlSessionFactoryBuilder = sqlSessionFactoryBuilder;
  }

  public void setTransactionFactory(TransactionFactory transactionFactory) {
    this.transactionFactory = transactionFactory;
  }

  public void setEnvironment(String environment) {
    this.environment = environment;
  }

  public void setScriptingLanguageDrivers(LanguageDriver... scriptingLanguageDrivers) {
    this.scriptingLanguageDrivers = scriptingLanguageDrivers;
  }

  public void setDefaultScriptingLanguageDriver(Class<? extends LanguageDriver> defaultScriptingLanguageDriver) {
    this.defaultScriptingLanguageDriver = defaultScriptingLanguageDriver;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    notNull(dataSource, "Property 'dataSource' is required");
    notNull(sqlSessionFactoryBuilder, "Property 'sqlSessionFactoryBuilder' is required");
    state((configuration == null && configLocation == null) || !(configuration != null && configLocation != null), "Property 'configuration' and 'configLocation' can not specified with together");

    this.sqlSessionFactory = buildSqlSessionFactory();
  }

  protected MbSqlSessionFactory buildSqlSessionFactory() throws Exception {

    final MbConfiguration targetConfiguration;

    MbXMLConfigBuilder xmlConfigBuilder = null;
    if (this.configuration != null) {
      targetConfiguration = this.configuration;
      if (targetConfiguration.getVariables() == null) {
        targetConfiguration.setVariables(this.configurationProperties);
      } else if (this.configurationProperties != null) {
        targetConfiguration.getVariables().putAll(this.configurationProperties);
      }
    } else if (this.configLocation != null) {
      xmlConfigBuilder = new MbXMLConfigBuilder(this.configLocation.getInputStream(), null, this.configurationProperties);
      targetConfiguration = xmlConfigBuilder.getConfiguration();
    } else {
      LOGGER.debug(() -> "Property 'configuration' or 'configLocation' not specified, using default MyBatis Configuration");
      targetConfiguration = new MbConfiguration();
      Optional.ofNullable(this.configurationProperties).ifPresent(targetConfiguration::setVariables);
    }

    Optional.ofNullable(this.objectFactory).ifPresent(targetConfiguration::setObjectFactory);
    Optional.ofNullable(this.objectWrapperFactory).ifPresent(targetConfiguration::setObjectWrapperFactory);
    Optional.ofNullable(this.vfs).ifPresent(targetConfiguration::setVfsImpl);

    if (hasLength(this.typeAliasesPackage)) {
      scanClasses(this.typeAliasesPackage, this.typeAliasesSuperType).stream().filter(clazz -> !clazz.isAnonymousClass()).filter(clazz -> !clazz.isInterface()).filter(clazz -> !clazz.isMemberClass()).forEach(targetConfiguration.getTypeAliasRegistry()::registerAlias);
    }

    if (!isEmpty(this.typeAliases)) {
      Stream.of(this.typeAliases).forEach(typeAlias -> {
        targetConfiguration.getTypeAliasRegistry().registerAlias(typeAlias);
        LOGGER.debug(() -> "Registered type alias: '" + typeAlias + "'");
      });
    }

    if (!isEmpty(this.plugins)) {
      Stream.of(this.plugins).forEach(plugin -> {
        targetConfiguration.addInterceptor(plugin);
        LOGGER.debug(() -> "Registered plugin: '" + plugin + "'");
      });
    }

    if (hasLength(this.typeHandlersPackage)) {
      scanClasses(this.typeHandlersPackage, TypeHandler.class).stream()
        .filter(clazz -> !clazz.isAnonymousClass())
        .filter(clazz -> !clazz.isInterface())
        .filter(clazz -> !Modifier.isAbstract(clazz.getModifiers()))
        .filter(clazz -> ClassUtils.getConstructorIfAvailable(clazz) != null)
        .forEach(targetConfiguration.getTypeHandlerRegistry()::register);
    }

    if (!isEmpty(this.typeHandlers)) {
      Stream.of(this.typeHandlers).forEach(typeHandler -> {
        targetConfiguration.getTypeHandlerRegistry().register(typeHandler);
        LOGGER.debug(() -> "Registered type handler: '" + typeHandler + "'");
      });
    }

    if (!isEmpty(this.scriptingLanguageDrivers)) {
      Stream.of(this.scriptingLanguageDrivers).forEach(languageDriver -> {
        targetConfiguration.getLanguageRegistry().register(languageDriver);
        LOGGER.debug(() -> "Registered scripting language driver: '" + languageDriver + "'");
      });
    }
    Optional.ofNullable(this.defaultScriptingLanguageDriver).ifPresent(targetConfiguration::setDefaultScriptingLanguage);

    if (this.databaseIdProvider != null) {// fix #64 set databaseId before parse mapper xmls
      try {
        targetConfiguration.setDatabaseId(this.databaseIdProvider.getDatabaseId(this.dataSource));
      } catch (SQLException e) {
        throw new IOException("Failed getting a databaseId", e);
      }
    }

    Optional.ofNullable(this.cache).ifPresent(targetConfiguration::addCache);

    if (xmlConfigBuilder != null) {
      try {
        xmlConfigBuilder.parse();
        LOGGER.debug(() -> "Parsed configuration file: '" + this.configLocation + "'");
      } catch (Exception ex) {
        throw new IOException("Failed to parse org.kayura.skeleton.config resource: " + this.configLocation, ex);
      } finally {
        ErrorContext.instance().reset();
      }
    }

    targetConfiguration.setEnvironment(new Environment(this.environment,
      this.transactionFactory == null
        ? new SpringManagedTransactionFactory()
        : this.transactionFactory,
      this.dataSource));

    if (this.mapperLocations != null) {
      if (this.mapperLocations.length == 0) {
        LOGGER.warn(() -> "Property 'mapperLocations' was specified but matching resources are not found.");
      } else {
        for (Resource mapperLocation : this.mapperLocations) {
          if (mapperLocation == null) {
            continue;
          }
          try {
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(),
              targetConfiguration,
              mapperLocation.toString(),
              targetConfiguration.getSqlFragments());
            xmlMapperBuilder.parse();
          } catch (Exception e) {
            throw new IOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
          } finally {
            ErrorContext.instance().reset();
          }
          LOGGER.debug(() -> "Parsed mapper file: '" + mapperLocation + "'");
        }
      }
    } else {
      LOGGER.debug(() -> "Property 'mapperLocations' was not specified.");
    }

    return this.sqlSessionFactoryBuilder.build(targetConfiguration);
  }

  @Override
  public MbSqlSessionFactory getObject() throws Exception {
    if (this.sqlSessionFactory == null) {
      afterPropertiesSet();
    }

    return this.sqlSessionFactory;
  }

  @Override
  public Class<? extends MbSqlSessionFactory> getObjectType() {
    return this.sqlSessionFactory == null ? MbSqlSessionFactory.class : this.sqlSessionFactory.getClass();
  }

  @Override
  public boolean isSingleton() {
    return true;
  }

  @Override
  public void onApplicationEvent(@NotNull ApplicationEvent event) {
    if (failFast && event instanceof ContextRefreshedEvent) {
      // fail-fast -> check all statements are completed
      this.sqlSessionFactory.getConfiguration().getMappedStatementNames();
    }
  }

  private Set<Class<?>> scanClasses(String packagePatterns, Class<?> assignableType) throws IOException {
    Set<Class<?>> classes = new HashSet<>();
    String[] packagePatternArray = tokenizeToStringArray(packagePatterns, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
    for (String packagePattern : packagePatternArray) {
      Resource[] resources = RESOURCE_PATTERN_RESOLVER.getResources(ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + ClassUtils.convertClassNameToResourcePath(packagePattern) + "/**/*.class");
      for (Resource resource : resources) {
        try {
          ClassMetadata classMetadata = METADATA_READER_FACTORY.getMetadataReader(resource).getClassMetadata();
          Class<?> clazz = Resources.classForName(classMetadata.getClassName());
          if (assignableType == null || assignableType.isAssignableFrom(clazz)) {
            classes.add(clazz);
          }
        } catch (Throwable e) {
          LOGGER.warn(() -> "Cannot load the '" + resource + "'. Cause by " + e.toString());
        }
      }
    }
    return classes;
  }

}
