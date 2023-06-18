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
