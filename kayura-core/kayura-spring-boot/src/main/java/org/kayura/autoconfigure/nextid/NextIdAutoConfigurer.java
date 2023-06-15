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
