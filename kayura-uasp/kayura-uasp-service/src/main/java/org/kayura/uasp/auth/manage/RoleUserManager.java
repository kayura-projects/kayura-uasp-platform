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

package org.kayura.uasp.auth.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.mapper.RoleUserMapper;
import org.kayura.uasp.role.RoleTypes;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleUserManager extends CrudManagerImpl<RoleUserMapper, RoleUserEntity> {

  protected RoleUserManager(RoleUserMapper baseMapper) {
    super(baseMapper);
  }

  public List<String> selectRoleCodesByFunc(String appId, String userId) {

    List<String> roleCodes = this.selectList(w -> {
      w.select(RoleUserEntity::getRoleCode);
      w.isNotNull(RoleUserEntity::getRoleCode);
      w.eq(RoleUserEntity::getRoleAppId, appId);
      w.eq(RoleUserEntity::getUserId, userId);
      w.eq(RoleUserEntity::getRoleType, RoleTypes.FUNC);
      w.eq(RoleUserEntity::getRoleStatus, DataStatus.Valid);
    }).stream().map(RoleUserEntity::getRoleCode).collect(Collectors.toList());
    return roleCodes;
  }
}
