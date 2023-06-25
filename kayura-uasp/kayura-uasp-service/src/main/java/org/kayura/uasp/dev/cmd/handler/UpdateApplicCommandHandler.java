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

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applic.ApplicPayload;
import org.kayura.uasp.dev.cmd.UpdateApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.utils.UaspConsts;
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

    if (UaspConsts.UASP_APP_CODE.equalsIgnoreCase(entity.getCode())) {
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
