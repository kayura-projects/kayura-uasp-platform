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
