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
