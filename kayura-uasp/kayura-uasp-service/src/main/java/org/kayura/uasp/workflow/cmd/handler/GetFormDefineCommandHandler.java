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
