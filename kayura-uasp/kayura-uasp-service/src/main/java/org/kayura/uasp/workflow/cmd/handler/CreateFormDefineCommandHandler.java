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
