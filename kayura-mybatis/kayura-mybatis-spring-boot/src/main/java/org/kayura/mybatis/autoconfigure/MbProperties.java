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
