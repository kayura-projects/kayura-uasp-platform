package org.kayura.autoconfigure.retry;

import org.kayura.security.retry.DefaultRetryLimitStrategy;
import org.kayura.security.retry.MessageResolver;
import org.kayura.security.retry.RetryLimitStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableConfigurationProperties({RetrySettings.class})
public class RetryAutoConfigurer {

  private final RetrySettings retrySettings;

  public RetryAutoConfigurer(RetrySettings retrySettings) {
    this.retrySettings = retrySettings;
  }

  @Bean
  @ConditionalOnMissingBean(RetryLimitStrategy.class)
  public RetryLimitStrategy retryLimitStrategy(CacheManager cacheManager, MessageResolver messageResolver) {
    List<Integer> policy = Arrays.asList(retrySettings.getPolicy());
    return new DefaultRetryLimitStrategy(cacheManager, messageResolver, policy);
  }

}
