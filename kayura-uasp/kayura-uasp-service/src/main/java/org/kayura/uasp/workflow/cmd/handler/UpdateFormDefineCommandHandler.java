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
