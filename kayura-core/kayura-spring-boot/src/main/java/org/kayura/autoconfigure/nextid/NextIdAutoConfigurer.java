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

package org.kayura.autoconfigure.nextid;

import org.kayura.nextid.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@ConditionalOnClass(IdGenerator.class)
public class NextIdAutoConfigurer {

  final static Map<NextIdTypes, Class<?>> MAPPERS = new HashMap<>();

  static {
    MAPPERS.put(NextIdTypes.UUID, UuidGenerator.class);
    MAPPERS.put(NextIdTypes.SNOWFLAKE, SnowflakeIdGenerator.class);
    MAPPERS.put(NextIdTypes.GUID, GuidGenerator.class);
    MAPPERS.put(NextIdTypes.RANDOM, RandomIdGenerator.class);
  }

  @Bean
  @ConditionalOnMissingBean(NextIdManager.class)
  public NextIdManager nextIdManagerBean() {
    DefaultNextIdManager idManager = new DefaultNextIdManager();
    idManager.addGenerator(Long.class, SnowflakeIdGenerator.INSTANCE);
    idManager.addGenerator(String.class, GuidGenerator.INSTANCE);
    idManager.addGenerator(UUID.class, UuidGenerator.INSTANCE);
    idManager.addGenerator(NextIdTypes.SNOWFLAKE, SnowflakeIdGenerator.INSTANCE);
    idManager.addGenerator(NextIdTypes.UUID, GuidGenerator.INSTANCE);
    idManager.addGenerator(NextIdTypes.GUID, UuidGenerator.INSTANCE);
    idManager.addGenerator(NextIdTypes.RANDOM, RandomIdGenerator.INSTANCE);
    return idManager;
  }

}
