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
