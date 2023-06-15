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
