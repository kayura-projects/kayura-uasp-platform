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

package org.kayura.uasp.ops.manage;

import org.kayura.security.sms.ProxySmsManager;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SmsManager {

  private final ProxySmsManager proxySmsManager;
  private final UserManager userManager;

  public SmsManager(ProxySmsManager proxySmsManager,
                    UserManager userManager) {
    this.proxySmsManager = proxySmsManager;
    this.userManager = userManager;
  }

  public void sendUserIds(String templateId, List<String> userIds, Map<String, Object> params) {

    List<UserEntity> list = this.userManager.selectList(w -> {
      w.select(UserEntity::getUserId);
      w.select(UserEntity::getMobile);
      w.in(UserEntity::getUserId, userIds);
    });

    List<String> mobiles = list.stream().map(UserEntity::getMobile)
      .distinct().collect(Collectors.toList());
    if (!mobiles.isEmpty()) {
      this.proxySmsManager.sendSms(templateId, mobiles, params);
    }
  }

  public void sendUserId(String templateId, String userId, Map<String, Object> params) {
    this.sendUserIds(templateId, Arrays.asList(userId), params);
  }

  public void sendMobile(String templateId, List<String> mobiles, Map<String, Object> params) {
    this.proxySmsManager.sendSms(templateId, mobiles, params);
  }

  public void sendMobile(String templateId, String mobile, Map<String, Object> params) {
    this.proxySmsManager.sendSms(templateId, Collections.singletonList(mobile), params);
  }
}
