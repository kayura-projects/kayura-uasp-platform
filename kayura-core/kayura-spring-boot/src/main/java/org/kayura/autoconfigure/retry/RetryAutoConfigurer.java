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
