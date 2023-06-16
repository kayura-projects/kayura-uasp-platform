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

import org.apache.ibatis.scripting.LanguageDriver;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriver;
import org.mybatis.scripting.freemarker.FreeMarkerLanguageDriverConfig;
import org.mybatis.scripting.thymeleaf.ThymeleafLanguageDriver;
import org.mybatis.scripting.thymeleaf.ThymeleafLanguageDriverConfig;
import org.mybatis.scripting.velocity.VelocityLanguageDriver;
import org.mybatis.scripting.velocity.VelocityLanguageDriverConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(LanguageDriver.class)
public class MbLanguageDriverAutoConfiguration {

  private static final String CONFIGURATION_PROPERTY_PREFIX = "mybatis.scripting-language-driver";

  @Configuration
  @ConditionalOnClass(FreeMarkerLanguageDriver.class)
  @ConditionalOnMissingClass("org.mybatis.scripting.freemarker.FreeMarkerLanguageDriverConfig")
  public static class LegacyFreeMarkerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    FreeMarkerLanguageDriver freeMarkerLanguageDriver() {
      return new FreeMarkerLanguageDriver();
    }
  }

  @Configuration
  @ConditionalOnClass({FreeMarkerLanguageDriver.class, FreeMarkerLanguageDriverConfig.class})
  public static class FreeMarkerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    FreeMarkerLanguageDriver freeMarkerLanguageDriver(FreeMarkerLanguageDriverConfig config) {
      return new FreeMarkerLanguageDriver(config);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".freemarker")
    public FreeMarkerLanguageDriverConfig freeMarkerLanguageDriverConfig() {
      return FreeMarkerLanguageDriverConfig.newInstance();
    }
  }

  @Configuration
  @ConditionalOnClass(org.mybatis.scripting.velocity.Driver.class)
  @ConditionalOnMissingClass("org.mybatis.scripting.velocity.VelocityLanguageDriverConfig")
  @SuppressWarnings("deprecation")
  public static class LegacyVelocityConfiguration {
    @Bean
    @ConditionalOnMissingBean
    org.mybatis.scripting.velocity.Driver velocityLanguageDriver() {
      return new org.mybatis.scripting.velocity.Driver();
    }
  }

  @Configuration
  @ConditionalOnClass({VelocityLanguageDriver.class, VelocityLanguageDriverConfig.class})
  public static class VelocityConfiguration {
    @Bean
    @ConditionalOnMissingBean
    VelocityLanguageDriver velocityLanguageDriver(VelocityLanguageDriverConfig config) {
      return new VelocityLanguageDriver(config);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".velocity")
    public VelocityLanguageDriverConfig velocityLanguageDriverConfig() {
      return VelocityLanguageDriverConfig.newInstance();
    }
  }

  @Configuration
  @ConditionalOnClass(ThymeleafLanguageDriver.class)
  public static class ThymeleafConfiguration {
    @Bean
    @ConditionalOnMissingBean
    ThymeleafLanguageDriver thymeleafLanguageDriver(ThymeleafLanguageDriverConfig config) {
      return new ThymeleafLanguageDriver(config);
    }

    @Bean
    @ConditionalOnMissingBean
    @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf")
    public ThymeleafLanguageDriverConfig thymeleafLanguageDriverConfig() {
      return ThymeleafLanguageDriverConfig.newInstance();
    }

    @SuppressWarnings("unused")
    private static class MetadataThymeleafLanguageDriverConfig extends ThymeleafLanguageDriverConfig {

      @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf.dialect")
      @Override
      public DialectConfig getDialect() {
        return super.getDialect();
      }

      @ConfigurationProperties(CONFIGURATION_PROPERTY_PREFIX + ".thymeleaf.template-file")
      @Override
      public TemplateFileConfig getTemplateFile() {
        return super.getTemplateFile();
      }
    }

  }


}
