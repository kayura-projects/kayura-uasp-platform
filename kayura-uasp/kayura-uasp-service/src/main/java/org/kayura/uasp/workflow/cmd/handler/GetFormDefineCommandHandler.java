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
import org.kayura.type.HttpResult;
import org.kayura.uasp.form.FormDefineVo;
import org.kayura.uasp.form.FormFieldVo;
import org.kayura.uasp.workflow.cmd.GetFormDefineCommand;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.entity.FormFieldEntity;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.uasp.workflow.manage.FormFieldManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetFormDefineCommandHandler implements CommandHandler<GetFormDefineCommand, HttpResult> {

  private final FormDefineManager defineManager;
  private final FormFieldManager fieldManager;
  private final ModelMapper modelMapper;

  public GetFormDefineCommandHandler(FormDefineManager defineManager,
                                     FormFieldManager fieldManager,
                                     ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.fieldManager = fieldManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetFormDefineCommand command) {

    String defineId = command.getFormId();

    FormDefineEntity entity = defineManager.selectById(defineId);
    if (entity == null) {
      return HttpResult.error("获取的记录不存在。");
    }

    List<FormFieldVo> fields = fieldManager.selectList(w -> w.eq(FormFieldEntity::getFormId, defineId))
      .stream()
      .map(m -> modelMapper.map(m, FormFieldVo.class))
      .collect(Collectors.toList());

    FormDefineVo defineVo = modelMapper.map(entity, FormDefineVo.class);
    defineVo.setFields(fields);

    return HttpResult.okBody(defineVo);
  }
}
