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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.account.AccountPayload;
import org.kayura.uasp.auth.cmd.AccountUpdateCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccountUpdateCommandHandler implements CommandHandler<AccountUpdateCommand, HttpResult> {

  private final UserManager userManager;
  private final SmsVerifyHandler smsVerifyHandler;

  public AccountUpdateCommandHandler(UserManager userManager,
                                     SmsVerifyHandler smsVerifyHandler) {
    this.userManager = userManager;
    this.smsVerifyHandler = smsVerifyHandler;
  }

  @Transactional
  public HttpResult execute(AccountUpdateCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AccountPayload payload = command.getPayload();

    // 如果修改了手机号，需要检查短信验证码
    if (userManager.mobileIsChange(loginUser.getUserId(), payload.getMobile())) {
      Result verify = smsVerifyHandler.verify(payload.getMobile(), payload.getUcode(), payload.getVcode());
      if (verify.isFail()) {
        return HttpResult.of(verify);
      }
    }

    UserEntity entity = userManager.selectById(loginUser.getUserId());
    entity.setAvatar(payload.getAvatar());
    entity.setDisplayName(payload.getDisplayName());
    entity.setMobile(payload.getMobile());
    entity.setEmail(payload.getEmail());
    userManager.updateById(loginUser.getUserId(), entity);

    return HttpResult.ok();
  }

}
