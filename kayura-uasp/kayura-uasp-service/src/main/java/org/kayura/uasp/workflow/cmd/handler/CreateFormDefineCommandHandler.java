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
