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
