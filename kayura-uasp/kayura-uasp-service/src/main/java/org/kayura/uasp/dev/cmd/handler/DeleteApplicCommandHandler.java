package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.DeleteApplicCommand;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteApplicCommandHandler implements CommandHandler<DeleteApplicCommand, HttpResult> {

  private static final String UASP_ERROR_MESSAGE = "UASP 为保留应用，不可删除。";
  private final ApplicManager applicManager;

  public DeleteApplicCommandHandler(ApplicManager applicManager) {
    this.applicManager = applicManager;
  }

  @Transactional
  public HttpResult execute(DeleteApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String appId = Optional.ofNullable(command.getAppId()).orElse(payload.getId());

    if (StringUtils.hasText(appId)) {
      if (UaspConstants.UASP_APP_ID.equals(appId)) {
        return HttpResult.error(UASP_ERROR_MESSAGE);
      }
      applicManager.deleteById(appId);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      if (payload.getIds().stream().anyMatch(UaspConstants.UASP_APP_ID::equals)) {
        return HttpResult.error(UASP_ERROR_MESSAGE);
      }
      applicManager.deleteByIds(payload.getIds());
    }

    return HttpResult.ok();
  }

}
