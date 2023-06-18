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
