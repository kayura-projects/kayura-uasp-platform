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
