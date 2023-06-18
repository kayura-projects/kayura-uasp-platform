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
