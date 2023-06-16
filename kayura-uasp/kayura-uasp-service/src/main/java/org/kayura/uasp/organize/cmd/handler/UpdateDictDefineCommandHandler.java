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
import org.kayura.type.UserTypes;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.dict.DictDefinePayload;
import org.kayura.uasp.dict.DictTypes;
import org.kayura.uasp.organize.cmd.UpdateDictDefineCommand;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateDictDefineCommandHandler implements CommandHandler<UpdateDictDefineCommand, HttpResult> {

  private final DictDefineManager defineManager;

  public UpdateDictDefineCommandHandler(DictDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional
  public HttpResult execute(UpdateDictDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DictDefinePayload payload = command.getPayload();
    String defineId = Optional.ofNullable(command.getDefineId()).orElse(payload.getDefineId());

    if (!UserTypes.ROOT.equals(loginUser.getUserType())) {
      return HttpResult.error("只能ROOT账号可以调用。");
    }

    if (StringUtils.isBlank(defineId)) {
      return HttpResult.error("必需指定字典定义ID。");
    }

    DictDefineEntity entity = defineManager.selectById(payload.getDefineId());
    if (entity == null) {
      return HttpResult.error("更新的数据不存在。");
    }

    if (DictTypes.Item.equals(entity.getType()) && !payload.getCode().equals(entity.getCode()) &&
      defineManager.selectCount(w -> {
        w.eq(DictDefineEntity::getCode, payload.getCode());
        w.notEq(DictDefineEntity::getDefineId, payload.getDefineId());
      }) > 0) {
      return HttpResult.error("编号已经存在。");
    }

    entity.setName(payload.getName());
    entity.setSort(payload.getSort());
    entity.setRemark(payload.getRemark());
    if (DictTypes.Item.equals(entity.getType())) {
      entity.setCode(payload.getCode());
      entity.setDataType(payload.getDataType());
      entity.setScope(payload.getScope());
      entity.setExtFields(payload.getExtFields());
    }
    defineManager.updateById(entity.getDefineId(), entity);

    return HttpResult.ok();
  }

}
