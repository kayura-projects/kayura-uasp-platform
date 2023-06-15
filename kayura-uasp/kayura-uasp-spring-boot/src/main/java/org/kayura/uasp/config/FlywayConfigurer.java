package org.kayura.uasp.config;

import org.springframework.boot.autoconfigure.flyway.FlywayConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfigurer {

  @Bean
  public FlywayConfigurationCustomizer flywayUaspCustomizer() {
    return (configuration) -> {
      configuration.locations("classpath:db/uasp");
      configuration.baselineOnMigrate(true);
      configuration.table("schema_history");
      configuration.validateOnMigrate(false);
    };
  }

}
