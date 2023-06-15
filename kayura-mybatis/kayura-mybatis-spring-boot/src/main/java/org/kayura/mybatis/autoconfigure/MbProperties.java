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

package org.kayura.mybatis.autoconfigure;

import org.kayura.mybatis.mapper.injector.IMethodInjector;
import org.kayura.mybatis.session.MbConfiguration;

import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Stream;

@ConfigurationProperties(prefix = MbProperties.MYBATIS_PREFIX)
public class MbProperties {

  public static final String MYBATIS_PREFIX = "mybatis";

  private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
  private String configLocation;
  private boolean paginator = true;
  private String[] mapperLocations;
  private String typeAliasesPackage;
  private Class<?> typeAliasesSuperType;
  private String typeHandlersPackage;
  private boolean checkConfigLocation = false;
  private ExecutorType executorType;
  private Class<? extends LanguageDriver> defaultScriptingLanguageDriver;
  private Properties configurationProperties;
  @NestedConfigurationProperty
  private MbConfiguration configuration;
  private IMethodInjector methodInjector;

  public String getConfigLocation() {
    return this.configLocation;
  }

  public void setConfigLocation(String configLocation) {
    this.configLocation = configLocation;
  }

  public String[] getMapperLocations() {
    return this.mapperLocations;
  }

  public void setMapperLocations(String[] mapperLocations) {
    this.mapperLocations = mapperLocations;
  }

  public String getTypeHandlersPackage() {
    return this.typeHandlersPackage;
  }

  public void setTypeHandlersPackage(String typeHandlersPackage) {
    this.typeHandlersPackage = typeHandlersPackage;
  }

  public String getTypeAliasesPackage() {
    return this.typeAliasesPackage;
  }

  public void setTypeAliasesPackage(String typeAliasesPackage) {
    this.typeAliasesPackage = typeAliasesPackage;
  }

  public Class<?> getTypeAliasesSuperType() {
    return typeAliasesSuperType;
  }

  public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
    this.typeAliasesSuperType = typeAliasesSuperType;
  }

  public boolean isCheckConfigLocation() {
    return this.checkConfigLocation;
  }

  public void setCheckConfigLocation(boolean checkConfigLocation) {
    this.checkConfigLocation = checkConfigLocation;
  }

  public ExecutorType getExecutorType() {
    return this.executorType;
  }

  public void setExecutorType(ExecutorType executorType) {
    this.executorType = executorType;
  }

  public Class<? extends LanguageDriver> getDefaultScriptingLanguageDriver() {
    return defaultScriptingLanguageDriver;
  }

  public void setDefaultScriptingLanguageDriver(Class<? extends LanguageDriver> defaultScriptingLanguageDriver) {
    this.defaultScriptingLanguageDriver = defaultScriptingLanguageDriver;
  }

  public Properties getConfigurationProperties() {
    return configurationProperties;
  }

  public void setConfigurationProperties(Properties configurationProperties) {
    this.configurationProperties = configurationProperties;
  }

  public MbConfiguration getConfiguration() {
    return configuration;
  }

  public void setConfiguration(MbConfiguration configuration) {
    this.configuration = configuration;
  }

  public Resource[] resolveMapperLocations() {
    return Stream.of(Optional.ofNullable(this.mapperLocations).orElse(new String[0]))
      .flatMap(location -> Stream.of(getResources(location))).toArray(Resource[]::new);
  }

  private Resource[] getResources(String location) {
    try {
      return resourceResolver.getResources(location);
    } catch (IOException e) {
      return new Resource[0];
    }
  }

  public boolean isPaginator() {
    return paginator;
  }

  public void setPaginator(boolean paginator) {
    this.paginator = paginator;
  }

  public IMethodInjector getMethodInjector() {
    return methodInjector;
  }

  public void setMethodInjector(IMethodInjector methodInjector) {
    this.methodInjector = methodInjector;
  }

}
