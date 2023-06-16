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

package org.kayura.autoconfigure.cache;

import org.kayura.autoconfigure.security.JwtSettings;
import org.kayura.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableConfigurationProperties({CacheSettings.class, JwtSettings.class})
@Import(RedisConfigurer.class)
public class CacheAutoConfigurer extends CachingConfigurerSupport {

  private static final Log logger = LogFactory.getLog(CacheAutoConfigurer.class);

  @Autowired
  private JwtSettings jwtSettings;
  @Autowired
  private CacheSettings cacheSettings;

  @Bean
  @ConditionalOnMissingBean(KeyGenerator.class)
  public KeyGenerator keyGenerator() {
    return new CacheKeyGenerator();
  }

  @Bean
  @ConditionalOnMissingBean(CacheManager.class)
  public CacheManager cacheManager(RedisConnectionFactory factory,
                                   ApplicationContext context,
                                   Jackson2JsonRedisSerializer<?> jacksonRedisSerializer) {

    RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
      .computePrefixWith(name -> "kayura:" + name + ":")
      .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jacksonRedisSerializer))
      .entryTtl(Duration.ofHours(2))
      .disableCachingNullValues();

    // 初始化缓存列表 及 JWT 默认缓存
    Map<String, Long> configNames = new HashMap<>();
    configNames.put(CacheSettings.JWT_TOKEN, jwtSettings.getExpire());

    // 添加代码配置缓存
    Map<String, CacheMapTtl> beans = context.getBeansOfType(CacheMapTtl.class);
    beans.forEach((beanName, beanObject) -> {
      if (logger.isInfoEnabled()) {
        logger.info("for " + beanName + " add cacheNames: " + StringUtils.join(beanObject.keySet(), ','));
      }
      configNames.putAll(beanObject);
    });

    Map<String, RedisCacheConfiguration> configMap = new HashMap<>();

    // 添加 application.yml 配置缓存
    configNames.putAll(cacheSettings.getNames());
    for (Map.Entry<String, Long> me : configNames.entrySet()) {
      configMap.put(me.getKey(), defaultConfig.entryTtl(Duration.ofSeconds(me.getValue())));
    }

    return RedisCacheManager.builder(factory)
      .initialCacheNames(configMap.keySet())
      .withInitialCacheConfigurations(configMap)
      .cacheDefaults(defaultConfig)
      .transactionAware()
      .build();
  }

}
