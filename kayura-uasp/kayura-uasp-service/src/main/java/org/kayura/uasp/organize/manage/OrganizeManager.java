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

package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.SelectManagerImpl;
import org.kayura.uasp.organize.entity.OrganizeEntity;
import org.kayura.uasp.organize.mapper.OrganizeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrganizeManager extends SelectManagerImpl<OrganizeMapper, OrganizeEntity> {

  protected OrganizeManager(OrganizeMapper baseMapper) {
    super(baseMapper);
  }

  public Map<String, String> queryIdNameMap(List<String> ids) {

    Map<String, String> nameMap = this.selectList(w -> {
      w.select(OrganizeEntity::getId);
      w.select(OrganizeEntity::getName);
      w.in(OrganizeEntity::getId, ids);
    }).stream().collect(
      Collectors.toMap(OrganizeEntity::getId, OrganizeEntity::getName)
    );
    return nameMap;
  }
}
