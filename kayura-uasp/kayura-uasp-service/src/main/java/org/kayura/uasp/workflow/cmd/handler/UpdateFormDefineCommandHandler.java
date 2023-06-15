/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
