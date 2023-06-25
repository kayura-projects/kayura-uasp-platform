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
import org.kayura.uasp.workflow.cmd.UpdateFormDefineCommand;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.entity.FormFieldEntity;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.uasp.workflow.manage.FormFieldManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class UpdateFormDefineCommandHandler implements CommandHandler<UpdateFormDefineCommand, HttpResult> {

  private final FormDefineManager defineManager;
  private final FormFieldManager fieldManager;

  public UpdateFormDefineCommandHandler(FormDefineManager defineManager,
                                        FormFieldManager fieldManager) {
    this.defineManager = defineManager;
    this.fieldManager = fieldManager;
  }

  @Transactional
  public HttpResult execute(UpdateFormDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    FormDefinePayload payload = command.getPayload();
    String formId = Optional.ofNullable(command.getFormId()).orElse(payload.getFormId());

    FormDefineEntity entity = defineManager.selectById(formId);
    if (entity == null) {
      return HttpResult.error("修改的表单定义记录不存在。");
    }

    if (!StringUtils.equalsIgnoreCase(entity.getCode(), payload.getCode()) &&
      defineManager.selectCount(w -> w.eq(FormDefineEntity::getCode, payload.getCode())
      ) > 0) {
      return HttpResult.error("编号 " + payload.getCode() + " 已经被占用。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setCategory(payload.getCategory());
    entity.setSort(payload.getSort());
    entity.setIcon(payload.getIcon());
    entity.setTable(payload.getTable());
    entity.setModuleId(StringUtils.trimToNull(payload.getModuleId()));
    entity.setType(payload.getType());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    defineManager.updateById(formId, entity);

    fieldManager.deleteByWhere(w -> w.eq(FormFieldEntity::getFormId, formId));
    fieldManager.batchInsert(formId, payload.getFields());

    return HttpResult.ok();
  }
}
