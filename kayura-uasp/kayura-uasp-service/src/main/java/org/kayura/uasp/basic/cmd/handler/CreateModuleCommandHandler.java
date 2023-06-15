package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.CodeName;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.cmd.CreateModuleCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.entity.ModuleActionEntity;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.dev.manage.ModuleActionManager;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.uasp.func.ActionTypes;
import org.kayura.uasp.func.ModulePayload;
import org.kayura.uasp.func.ModuleVo;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateModuleCommandHandler implements CommandHandler<CreateModuleCommand, HttpResult> {

  private final ApplicManager applicManager;
  private final ModuleDefineManager defineManager;
  private final ModuleActionManager actionManager;
  private final ModelMapper modelMapper;

  public CreateModuleCommandHandler(ApplicManager applicManager,
                                    ModuleDefineManager defineManager,
                                    ModuleActionManager actionManager,
                                    ModelMapper modelMapper) {
    this.applicManager = applicManager;
    this.defineManager = defineManager;
    this.actionManager = actionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateModuleCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ModulePayload payload = command.getPayload();

    if (applicManager.selectCount(w -> {
      w.eq(ApplicEntity::getAppId, payload.getAppId());
    }) == 0) {
      return HttpResult.error("指定的应用ID不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) &&
      defineManager.selectCount(w -> {
        w.eq(ModuleDefineEntity::getAppId, payload.getAppId());
        w.eq(ModuleDefineEntity::getCode, payload.getCode());
      }) > 0) {
      return HttpResult.error("编号已经存在，不允许重复。");
    }

    ModuleDefineEntity entity = ModuleDefineEntity.create();
    entity.setModuleId(defineManager.nextId());
    entity.setAppId(payload.getAppId());
    entity.setParentId(payload.getParentId());
    entity.setType(payload.getType());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setIcon(payload.getIcon());
    entity.setUrl(payload.getUrl());
    entity.setTarget(payload.getTarget());
    entity.setSort(payload.getSort());
    entity.setUsage(payload.getUsage());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    defineManager.insertOne(entity);

    // 保存操作项
    this.batchInsertActions(payload, entity);

    ModuleVo model = modelMapper.map(entity, ModuleVo.class);
    return HttpResult.okBody(model);
  }

  private void batchInsertActions(ModulePayload payload, ModuleDefineEntity entity) {

    List<CodeName> actions = payload.getActions();
    if (CollectionUtils.isNotEmpty(actions)) {
      List<ModuleActionEntity> collect = new ArrayList<>();
      for (int i = 0; i < actions.size(); i++) {
        ModuleActionEntity action = ModuleActionEntity.create()
          .setModuleId(entity.getModuleId())
          .setType(ActionTypes.Func)
          .setCode(actions.get(i).getCode())
          .setName(actions.get(i).getName())
          .setSort(i);
        collect.add(action);
      }
      actionManager.insertBatch(collect);
    }
  }
}
