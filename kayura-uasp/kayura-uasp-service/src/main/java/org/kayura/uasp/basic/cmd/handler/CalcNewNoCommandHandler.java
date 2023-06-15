package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.autono.NewNoArgs;
import org.kayura.uasp.basic.cmd.CalcNewNoCommand;
import org.kayura.uasp.basic.manage.AutoNoManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CalcNewNoCommandHandler implements CommandHandler<CalcNewNoCommand, HttpResult> {

  private final AutoNoManager autoNoManager;

  public CalcNewNoCommandHandler(AutoNoManager autoNoManager) {
    this.autoNoManager = autoNoManager;
  }

  @Transactional
  public HttpResult execute(CalcNewNoCommand command) {

    LoginUser loginUser = command.getLoginUser();
    NewNoArgs args = command.getArgs();
    boolean fixed = command.isFixed();

    if (loginUser.hasTenantUser()) {
      args.setTenantId(loginUser.getTenantId());
    }
    if (StringUtils.isBlank(args.getTenantId())) {
      return HttpResult.error("必需要指定租户ID。");
    }

    String newNo = autoNoManager.calcNewNo(args, fixed);
    return HttpResult.okBody(newNo);
  }

}
