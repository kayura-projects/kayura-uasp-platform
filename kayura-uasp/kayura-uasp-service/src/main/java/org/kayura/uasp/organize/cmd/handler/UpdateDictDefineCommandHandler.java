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
