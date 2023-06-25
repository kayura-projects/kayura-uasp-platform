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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.form.FormDefinePayload;
import org.kayura.uasp.form.FormDefineVo;
import org.kayura.uasp.workflow.cmd.CreateFormDefineCommand;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.uasp.workflow.manage.FormFieldManager;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateFormDefineCommandHandler implements CommandHandler<CreateFormDefineCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final FormDefineManager defineManager;
  private final FormFieldManager fieldManager;

  public CreateFormDefineCommandHandler(ModelMapper modelMapper,
                                        FormDefineManager defineManager,
                                        FormFieldManager fieldManager) {
    this.modelMapper = modelMapper;
    this.defineManager = defineManager;
    this.fieldManager = fieldManager;
  }

  @Transactional
  public HttpResult execute(CreateFormDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormDefinePayload payload = command.getPayload();

    if (defineManager.selectCount(w ->
      w.eq(FormDefineEntity::getCode, payload.getCode())
    ) > 0) {
      return HttpResult.error("编号 " + payload.getCode() + " 已经被占用。");
    }

    FormDefineEntity entity = FormDefineEntity.create()
      .setFormId(defineManager.nextId())
      .setAppId(payload.getAppId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setCategory(payload.getCategory())
      .setSort(payload.getSort())
      .setIcon(payload.getIcon())
      .setTable(payload.getTable())
      .setModuleId(StringUtils.trimToNull(payload.getModuleId()))
      .setType(payload.getType())
      .setStatus(payload.getStatus())
      .setRemark(payload.getRemark());
    defineManager.insertOne(entity);
    fieldManager.batchInsert(entity.getFormId(), payload.getFields());

    FormDefineVo model = modelMapper.map(entity, FormDefineVo.class);
    return HttpResult.okBody(model);
  }
}
