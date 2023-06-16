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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.uasp.dict.DictItemPayload;
import org.kayura.uasp.organize.cmd.UpdateDictItemCommand;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateDictItemCommandHandler implements CommandHandler<UpdateDictItemCommand, HttpResult> {

  private final DictItemManager itemManager;

  public UpdateDictItemCommandHandler(DictItemManager itemManager) {
    this.itemManager = itemManager;
  }

  @Transactional
  public HttpResult execute(UpdateDictItemCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DictItemPayload payload = command.getPayload();
    String itemId = Optional.ofNullable(command.getItemId()).orElse(payload.getItemId());

    DictItemEntity entity = itemManager.selectById(itemId);
    if (entity == null) {
      return HttpResult.error("更新的字典项不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) &&
      !StringUtils.equals(entity.getCode(), payload.getCode()) &&
      itemManager.selectCount(w -> {
        w.eq(DictItemEntity::getDefineId, payload.getDefineId());
        w.eq(DictItemEntity::getCode, payload.getCode());
      }) > 0) {
      return HttpResult.error("编号已经存在。");
    }

    if (StringUtils.hasText(payload.getName()) &&
      !StringUtils.equals(entity.getName(), payload.getName()) &&
      itemManager.selectCount(w -> {
        w.eq(DictItemEntity::getDefineId, payload.getDefineId());
        w.eq(DictItemEntity::getName, payload.getName());
      }) > 0) {
      return HttpResult.error("名称已经存在。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setSort(payload.getSort());
    entity.setStatus(payload.getStatus());
    entity.setExtField1(payload.getExtField1());
    entity.setExtField2(payload.getExtField2());
    entity.setExtField3(payload.getExtField3());
    entity.setExtField4(payload.getExtField4());
    entity.setExtField5(payload.getExtField5());
    itemManager.updateById(entity.getItemId(), entity);

    return HttpResult.ok();
  }

}
