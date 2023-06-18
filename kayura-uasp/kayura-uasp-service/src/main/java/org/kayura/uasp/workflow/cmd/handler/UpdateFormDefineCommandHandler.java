/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
