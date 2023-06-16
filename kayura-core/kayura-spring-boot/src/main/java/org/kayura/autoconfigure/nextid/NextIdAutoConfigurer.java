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
