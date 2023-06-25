/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
