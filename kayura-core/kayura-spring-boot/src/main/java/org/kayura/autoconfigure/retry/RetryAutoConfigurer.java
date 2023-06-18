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
