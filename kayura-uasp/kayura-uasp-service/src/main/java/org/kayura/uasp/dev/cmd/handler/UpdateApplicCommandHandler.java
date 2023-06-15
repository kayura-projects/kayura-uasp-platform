package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applic.ApplicPayload;
import org.kayura.uasp.dev.cmd.UpdateApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.utils.UaspConstants;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateApplicCommandHandler implements CommandHandler<UpdateApplicCommand, HttpResult> {

  private final ApplicManager applicManager;

  public UpdateApplicCommandHandler(ApplicManager applicManager) {
    this.applicManager = applicManager;
  }

  @Transactional
  public HttpResult execute(UpdateApplicCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ApplicPayload payload = command.getPayload();
    String appId = Optional.ofNullable(command.getAppId()).orElse(payload.getAppId());

    ApplicEntity entity = applicManager.selectById(appId);
    if (entity == null) {
      return HttpResult.error("更新的应用不存在。");
    }

    if (UaspConstants.UASP_APP_CODE.equalsIgnoreCase(entity.getCode())) {
      return HttpResult.error("UASP 为保留应用，不可编辑。");
    }

    if (DataStatus.Draft.equals(payload.getStatus()) && !entity.getStatus().equals(DataStatus.Draft)) {
      return HttpResult.error("应用已经被启用过，不可重设置为“草稿”状态。");
    }

    if (!entity.getCode().equalsIgnoreCase(payload.getCode()) &&
      applicManager.selectCount(w -> {
        w.eq(ApplicEntity::getCode, payload.getCode());
        w.notEq(ApplicEntity::getAppId, appId);
      }) > 0) {
      return HttpResult.error("应用编码已经被占用。");
    }

    if (!entity.getName().equalsIgnoreCase(payload.getName()) &&
      applicManager.selectCount(w -> {
        w.eq(ApplicEntity::getName, payload.getName());
        w.notEq(ApplicEntity::getAppId, appId);
      }) > 0) {
      return HttpResult.error("应用名称已经被占用。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setLevel(payload.getLevel());
    entity.setType(payload.getType());
    entity.setUrl(payload.getUrl());
    entity.setSort(payload.getSort());
    entity.setNeedRelease(payload.getNeedRelease());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    applicManager.updateById(appId, entity);

    return HttpResult.ok();
  }

}
