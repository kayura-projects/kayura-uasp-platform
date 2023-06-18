/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
 -----------------------------------------------------------------------------*/

package org.kayura.autoconfigure.cache;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RedisConfigurer {

  private final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

  public RedisConfigurer(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
    this.jackson2ObjectMapperBuilder = jackson2ObjectMapperBuilder;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(factory);
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(jacksonRedisSerializer());
    template.afterPropertiesSet();
    return template;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  @Bean
  public Jackson2JsonRedisSerializer<?> jacksonRedisSerializer() {

    ObjectMapper objectMapper = new ObjectMapper();
    jackson2ObjectMapperBuilder.configure(objectMapper);
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    Jackson2JsonRedisSerializer<?> valueSerializer = new Jackson2JsonRedisSerializer(Object.class);
    valueSerializer.setObjectMapper(objectMapper);
    return valueSerializer;
  }
}
