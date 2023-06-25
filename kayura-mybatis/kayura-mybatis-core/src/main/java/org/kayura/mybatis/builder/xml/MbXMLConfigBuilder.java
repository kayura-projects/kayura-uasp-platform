/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.builder.xml;

import org.kayura.mybatis.session.MbConfiguration;

import org.apache.ibatis.builder.BaseBuilder;
import org.apache.ibatis.builder.BuilderException;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.builder.xml.XMLMapperEntityResolver;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.loader.ProxyFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.parsing.XNode;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.*;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.type.JdbcType;

import javax.sql.DataSource;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

/**
 * 对应重写的 {@link XMLConfigBuilder} 类。
 *
 * @author LiangXia@live.com
 */
public class MbXMLConfigBuilder extends BaseBuilder {

  private final XPathParser parser;
  private final ReflectorFactory reflectorFactory = new DefaultReflectorFactory();
  private boolean parsed;
  private String environment;

  public MbXMLConfigBuilder(Reader reader) {
    this(reader, null, null);
  }

  public MbXMLConfigBuilder(Reader reader, String environment) {
    this(reader, environment, null);
  }

  public MbXMLConfigBuilder(Reader reader, String environment, Properties variables) {
    this(new XPathParser(reader, true, variables, new XMLMapperEntityResolver()), environment, variables);
  }

  public MbXMLConfigBuilder(InputStream inputStream) {
    this(inputStream, null, null);
  }

  public MbXMLConfigBuilder(InputStream inputStream, String environment) {
    this(inputStream, environment, null);
  }

  public MbXMLConfigBuilder(InputStream inputStream, String environment, Properties variables) {
    this(new XPathParser(inputStream, true, variables, new XMLMapperEntityResolver()), environment, variables);
  }

  public MbXMLConfigBuilder(XPathParser parser, String environment, Properties variables) {
    super(new MbConfiguration());
    ErrorContext.instance().resource("SQL Mapper Configuration");
    this.configuration.setVariables(variables);
    this.parsed = false;
    this.environment = environment;
    this.parser = parser;
  }

  @Override
  public MbConfiguration getConfiguration() {
    return (MbConfiguration) super.getConfiguration();
  }

  public MbConfiguration parse() {
    if (parsed) {
      throw new BuilderException("每个 XMLConfigBuilder 只能执行一次。");
    }
    parsed = true;
    parseConfiguration(parser.evalNode("/configuration"));
    return getConfiguration();
  }

  /**
   * 解析 MybatisConfig.xml 文件内容
   */
  private void parseConfiguration(XNode root) {
    try {
      propertiesElement(root.evalNode("properties"));
      Properties settings = settingsAsProperties(root.evalNode("settings"));
      loadCustomVfs(settings);
      loadCustomLogImpl(settings);
      typeAliasesElement(root.evalNode("typeAliases"));
      pluginElement(root.evalNode("plugins"));
      objectFactoryElement(root.evalNode("objectFactory"));
      objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
      reflectorFactoryElement(root.evalNode("reflectorFactory"));
      settingsElement(settings);
      environmentsElement(root.evalNode("environments"));
      databaseIdProviderElement(root.evalNode("databaseIdProvider"));
      typeHandlerElement(root.evalNode("typeHandlers"));
      mapperElement(root.evalNode("mappers"));
    } catch (Exception e) {
      throw new BuilderException("错误的解析了映射配置文件。原因: " + e, e);
    }
  }

  /**
   * 获取 mappers 配置节，并解析所有内容。
   */
  private void mapperElement(XNode parent) throws Exception {

    // <mappers>
    // <package name="org.kayura.mybatis.mapper" />
    // <mapper resource="org/apache/ibatis/builder/AuthorMapper.xml"/>
    // <mapper url="org/apache/ibatis/builder/AuthorMapper.xml"/>
    // <mapper class="org.kayura.mybatis.mapper.AuthorMapper"/>
    // </mappers>

    if (parent != null) {
      for (XNode child : parent.getChildren()) {
        if ("package".equals(child.getName())) {
          // 扫描包下的所有接口，并使用 MapperAnnotationBuilder 解析。
          // 通过 MapperProxyFactory 创建其动态创建对象
          String mapperPackage = child.getStringAttribute("name");
          configuration.addMappers(mapperPackage);
        } else {
          // 使用 Mapper.xml 文件扫描
          String resource = child.getStringAttribute("resource");
          String url = child.getStringAttribute("url");
          String mapperClass = child.getStringAttribute("class");
          if (resource != null && url == null && mapperClass == null) {
            ErrorContext.instance().resource(resource);
            InputStream inputStream = Resources.getResourceAsStream(resource);
            // 加载 mapper.xml，并将解析结果添加至 configuration
            XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
            builder.parse();
          } else if (resource == null && url != null && mapperClass == null) {
            ErrorContext.instance().resource(url);
            InputStream inputStream = Resources.getUrlAsStream(url);
            // 加载 mapper.xml，并将解析结果添加至 configuration
            XMLMapperBuilder builder = new XMLMapperBuilder(inputStream, configuration, url, configuration.getSqlFragments());
            builder.parse();
          } else if (resource == null && url == null && mapperClass != null) {
            Class<?> mapperInterface = Resources.classForName(mapperClass);
            // 注册 Mapper 接口,并使用 MapperAnnotationBuilder 解析。
            // 通过 MapperProxyFactory 创建其动态创建对象
            configuration.addMapper(mapperInterface);
          } else {
            throw new BuilderException("mapper 元素只能指定url、resource 或 class，仅能指定其中一个。");
          }
        }
      }
    }

  }

  /**
   * 获取 typeHandlers 配置，并向 typeHandlerRegistry 中注册。
   */
  private void typeHandlerElement(XNode parent) {
    if (parent != null) {
      for (XNode child : parent.getChildren()) {
        if ("package".equals(child.getName())) {
          String typeHandlerPackage = child.getStringAttribute("name");
          typeHandlerRegistry.register(typeHandlerPackage);
        } else {
          String javaTypeName = child.getStringAttribute("javaType");
          String jdbcTypeName = child.getStringAttribute("jdbcType");
          String handlerTypeName = child.getStringAttribute("handler");
          Class<?> javaTypeClass = resolveClass(javaTypeName);
          JdbcType jdbcType = resolveJdbcType(jdbcTypeName);
          Class<?> handlerClass = resolveClass(handlerTypeName);
          if (javaTypeClass != null) {
            if (jdbcType != null) {
              typeHandlerRegistry.register(javaTypeClass, jdbcType, handlerClass);
            } else {
              typeHandlerRegistry.register(javaTypeClass, handlerClass);
            }
          } else {
            typeHandlerRegistry.register(handlerClass);
          }
        }
      }
    }
  }

  /**
   * 获取 databaseIdProvider 配置，并计算设设置 databaseId 值。
   */
  private void databaseIdProviderElement(XNode context) throws Exception {
    DatabaseIdProvider idProvider = null;
    if (context != null) {
      String type = context.getStringAttribute("type");
      // 向后兼容
      if ("VENDOR".equals(type)) {
        type = "DB_VENDOR";
      }
      Properties props = context.getChildrenAsProperties();
      idProvider = (DatabaseIdProvider) resolveClass(type).getDeclaredConstructor().newInstance();
      idProvider.setProperties(props);
    }

    Environment env = configuration.getEnvironment();
    if (env != null && idProvider != null) {
      String databaseId = idProvider.getDatabaseId(env.getDataSource());
      configuration.setDatabaseId(databaseId);
    }
  }

  /**
   * 读取并设置环境变量
   */
  private void environmentsElement(XNode context) throws Exception {
    if (context != null) {
      if (environment == null) {
        environment = context.getStringAttribute("default");
      }
      for (XNode child : context.getChildren()) {
        String id = child.getStringAttribute("id");
        if (isSpecifiedEnvironment(id)) {
          TransactionFactory txFactory = transactionManagerElement(child.evalNode("transactionManager"));
          DataSourceFactory dsFactory = dataSourceElement(child.evalNode("dataSource"));
          DataSource dataSource = dsFactory.getDataSource();
          Environment.Builder builder = new Environment.Builder(id).transactionFactory(txFactory).dataSource(dataSource);
          configuration.setEnvironment(builder.build());
        }
      }
    }
  }

  private DataSourceFactory dataSourceElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      Properties props = context.getChildrenAsProperties();
      DataSourceFactory factory = (DataSourceFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      factory.setProperties(props);
      return factory;
    }
    throw new BuilderException("环境定义缺少 DataSourceFactory。");
  }

  private TransactionFactory transactionManagerElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      Properties props = context.getChildrenAsProperties();
      TransactionFactory factory = (TransactionFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      factory.setProperties(props);
      return factory;
    }
    throw new BuilderException("环境定义缺少 TransactionFactory.");
  }

  private boolean isSpecifiedEnvironment(String id) {
    if (environment == null) {
      throw new BuilderException("未指定 Environment。");
    } else if (id == null) {
      throw new BuilderException("环境定义缺少 id 属性.");
    } else return environment.equals(id);
  }

  /**
   * 设置其它参数配置属性。
   */
  private void settingsElement(Properties settings) {
    // 指定是否和如何自动将列映射到字段/属性。
    configuration.setAutoMappingBehavior(AutoMappingBehavior.valueOf(settings.getProperty("autoMappingBehavior", "PARTIAL")));
    // 当检测到自动映射目标的未知列(或未知属性类型)时，指定行为。
    configuration.setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior.valueOf(settings.getProperty("autoMappingUnknownColumnBehavior", "NONE")));
    // 是否启用缓存，默认为 true
    configuration.setCacheEnabled(booleanValueOf(settings.getProperty("cacheEnabled"), true));
    // 设置动态代理工厂实例，默认为 JavassistProxyFactory
    configuration.setProxyFactory((ProxyFactory) createInstance(settings.getProperty("proxyFactory")));
    // 延迟加载的全局开关，默认为 false
    configuration.setLazyLoadingEnabled(booleanValueOf(settings.getProperty("lazyLoadingEnabled"), false));
    // 是否开启完整加载，默认为 false 即按需加载
    configuration.setAggressiveLazyLoading(booleanValueOf(settings.getProperty("aggressiveLazyLoading"), false));
    // 是否允许单一语句返回多结果集，默认为 true
    configuration.setMultipleResultSetsEnabled(booleanValueOf(settings.getProperty("multipleResultSetsEnabled"), true));
    // 是否使用列标签代替列名，默认为 true
    configuration.setUseColumnLabel(booleanValueOf(settings.getProperty("useColumnLabel"), true));
    // 允许 JDBC 支持自动生成主键，默认为 false
    configuration.setUseGeneratedKeys(booleanValueOf(settings.getProperty("useGeneratedKeys"), false));
    // 配置默认的执行器。SIMPLE 就是普通的执行器
    configuration.setDefaultExecutorType(ExecutorType.valueOf(settings.getProperty("defaultExecutorType", "SIMPLE")));
    // 控制返回结果的获取大小
    configuration.setDefaultFetchSize(integerValueOf(settings.getProperty("defaultFetchSize"), null));
    // 是否开启自动驼峰命名规则映射, A_COLUMN 到 java 属性名映射，默认 true
    configuration.setMapUnderscoreToCamelCase(booleanValueOf(settings.getProperty("mapUnderscoreToCamelCase"), true));
    // 本地缓存机制，SESSION 会缓存一个会话中执行的所有查询
    configuration.setLocalCacheScope(LocalCacheScope.valueOf(settings.getProperty("localCacheScope", "SESSION")));
    // 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。
    configuration.setJdbcTypeForNull(JdbcType.valueOf(settings.getProperty("jdbcTypeForNull", "OTHER")));
    // 指定哪个对象的方法触发一次延迟加载。
    configuration.setLazyLoadTriggerMethods(stringSetValueOf(settings.getProperty("lazyLoadTriggerMethods"), "equals,clone,hashCode,toString"));
    // 启用安全嵌套语句中使用分页, 默认 false 表示允许
    configuration.setSafeRowBoundsEnabled(booleanValueOf(settings.getProperty("safeRowBoundsEnabled"), false));
    // 启用安全的结果处理器，默认 true 表示不允许使用自定义 ResultHandler.
    configuration.setSafeResultHandlerEnabled(booleanValueOf(settings.getProperty("safeResultHandlerEnabled"), true));
    // 设置默认的脚本驱动语言
    configuration.setDefaultScriptingLanguage(resolveClass(settings.getProperty("defaultScriptingLanguage")));
    // 设置默认的枚举类型处理器
    configuration.setDefaultEnumTypeHandler(resolveClass(settings.getProperty("defaultEnumTypeHandler")));
    // 是否启用当值为NULL时也进行 setter 操作，默认为 false
    configuration.setCallSettersOnNulls(booleanValueOf(settings.getProperty("callSettersOnNulls"), false));
    // 设置是否使用真实的参数名 {@link ParamNameResolver}
    configuration.setUseActualParamName(booleanValueOf(settings.getProperty("useActualParamName"), true));
    // 当返回行的所有列都是空时，MyBatis默认返回null。当开启这个设置时，MyBatis会返回一个空实例。请注意，它也适用于嵌套的结果集
    configuration.setReturnInstanceForEmptyRow(booleanValueOf(settings.getProperty("returnInstanceForEmptyRow"), false));
    // 指定MyBatis增加到日志名称的前缀。
    configuration.setLogPrefix(settings.getProperty("logPrefix"));
    // 指定一个提供Configuration实例的类。这个被返回的Configuration实例用来加载被反序列化对象的懒加载属性值。
    // 这个类必须包含一个签名方法static Configuration getConfiguration()。
    configuration.setConfigurationFactory(resolveClass(settings.getProperty("configurationFactory")));
  }

  /**
   * 解析 reflectorFactory 节点，并设置 反射工厂 实例。
   *
   * @param context @throws Exception @throws
   */
  private void reflectorFactoryElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      ReflectorFactory reflectorFactory = (ReflectorFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      configuration.setReflectorFactory(reflectorFactory);
    }
  }

  /**
   * 解析 objectWrapperFactory 节点，并设置 对象包装工厂 实例。
   */
  private void objectWrapperFactoryElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      ObjectWrapperFactory wrapperFactory = (ObjectWrapperFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      configuration.setObjectWrapperFactory(wrapperFactory);
    }
  }

  /**
   * 解析 objectFactory 节点，并配置 对象工厂 实例。
   */
  private void objectFactoryElement(XNode context) throws Exception {
    if (context != null) {
      String type = context.getStringAttribute("type");
      Properties properties = context.getChildrenAsProperties();
      ObjectFactory factory = (ObjectFactory) resolveClass(type).getDeclaredConstructor().newInstance();
      factory.setProperties(properties);
      configuration.setObjectFactory(factory);
    }
  }

  /**
   * 解析 plugin 节点，并注册插件。
   */
  private void pluginElement(XNode context) throws Exception {
    if (context != null) {
      for (XNode child : context.getChildren()) {
        String interceptor = child.getStringAttribute("interceptor");
        Properties properties = child.getChildrenAsProperties();
        Interceptor interceptorInstance = (Interceptor) resolveClass(interceptor).getDeclaredConstructor().newInstance();
        interceptorInstance.setProperties(properties);
        configuration.addInterceptor(interceptorInstance);
      }
    }
  }

  /**
   * 解析 typeAliases 节点下的类型别名。
   */
  private void typeAliasesElement(XNode context) {
    if (context != null) {
      for (XNode child : context.getChildren()) {
        if ("package".equals(child.getName())) {
          String typeAliasPackage = child.getStringAttribute("name");
          typeAliasRegistry.registerAliases(typeAliasPackage);
        } else {
          String alias = child.getStringAttribute("alias");
          String type = child.getStringAttribute("type");
          try {
            Class<?> clazz = Resources.classForName(type);
            if (alias == null) {
              typeAliasRegistry.registerAlias(clazz);
            } else {
              typeAliasRegistry.registerAlias(alias, clazz);
            }
          } catch (Exception e) {
            throw new BuilderException("类型别名 '" + alias + "' 注册错误. 原因: " + e, e);
          }
        }
      }
    }

  }

  /**
   * 解析文件中的 properties 元素
   */
  private void propertiesElement(XNode context) throws Exception {
    if (context != null) {
      Properties defaults = context.getChildrenAsProperties();
      String resource = context.getStringAttribute("resource");
      String url = context.getStringAttribute("url");
      if (resource != null && url != null) {
        throw new BuilderException("不能同时指定 url 和基于 resource 的属性文件引用，请指定其中一个。");
      }
      if (resource != null) {
        defaults.putAll(Resources.getResourceAsProperties(resource));
      } else if (url != null) {
        defaults.putAll(Resources.getUrlAsProperties(url));
      }
      // 因为构造时的变量，优先级是高于配置的。
      // 所以拿出变量集，再重新添加至属性集中。
      Properties variables = configuration.getVariables();
      if (variables != null) {
        defaults.putAll(variables);
      }
      parser.setVariables(defaults);
      configuration.setVariables(defaults);
    }
  }

  /**
   * 获取 {@link Configuration} 属性设置。
   */
  private Properties settingsAsProperties(XNode context) {
    if (context == null) {
      return new Properties();
    }
    Properties properties = context.getChildrenAsProperties();
    // Check that all settings are known to the configuration class
    MetaClass metaClass = MetaClass.forClass(Configuration.class, reflectorFactory);
    for (Object key : properties.keySet()) {
      if (!metaClass.hasSetter(String.valueOf(key))) {
        throw new BuilderException("这个 " + key + " 是未知的设置项。请确保拼写正确(区分大小写)。");
      }
    }
    return properties;
  }

  /**
   * 加载 VFS 虚拟文件系统 访问实现类
   */
  private void loadCustomVfs(Properties settings) throws ClassNotFoundException {
    String value = settings.getProperty("vfsImpl");
    if (value != null) {
      String[] list = value.split(",");
      for (String clazz : list) {
        if (!clazz.isEmpty()) {
          @SuppressWarnings("unchecked") Class<? extends VFS> vfsImpl = (Class<? extends VFS>) Resources.classForName(clazz);
          configuration.setVfsImpl(vfsImpl);
        }
      }
    }
  }

  /**
   * 加载 Log 日志输出实现类
   */
  private void loadCustomLogImpl(Properties settings) {
    Class<? extends Log> logImpl = resolveClass(settings.getProperty("logImpl"));
    configuration.setLogImpl(logImpl);
  }

}
