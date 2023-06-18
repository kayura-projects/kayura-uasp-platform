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

package org.kayura.uasp.dev.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.mapper.ApplicMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicManager extends CrudManagerImpl<ApplicMapper, ApplicEntity> {

  protected ApplicManager(ApplicMapper baseMapper) {
    super(baseMapper);
  }

  public String findAppIdByCode(String appCode) {

    ApplicEntity entity = this.selectOne(w -> {
      w.select(ApplicEntity::getAppId);
      w.eq(ApplicEntity::getCode, appCode);
    });
    return entity != null ? entity.getAppId() : null;
  }

}
