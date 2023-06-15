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
