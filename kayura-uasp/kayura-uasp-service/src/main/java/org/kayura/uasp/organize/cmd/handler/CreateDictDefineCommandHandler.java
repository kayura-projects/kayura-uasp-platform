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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.dict.DictDefinePayload;
import org.kayura.uasp.dict.DictDefineVo;
import org.kayura.uasp.dict.DictTypes;
import org.kayura.uasp.organize.cmd.CreateDictDefineCommand;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateDictDefineCommandHandler implements CommandHandler<CreateDictDefineCommand, HttpResult> {

  private final DictDefineManager defineManager;
  private final ModelMapper modelMapper;

  public CreateDictDefineCommandHandler(DictDefineManager defineManager,
                                        ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateDictDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DictDefinePayload payload = command.getPayload();

    if (!UserTypes.ROOT.equals(loginUser.getUserType())) {
      return HttpResult.error("只能ROOT账号可以调用。");
    }

    if (defineManager.selectCount(w ->
      w.eq(DictDefineEntity::getCode, payload.getCode())
    ) > 0) {
      return HttpResult.error("字典定义ID重复。");
    }

    if (DictTypes.Item.equals(payload.getType())) {
      if (StringUtils.isBlank(payload.getCode())) {
        return HttpResult.error("字典项的定义，必需指定编号。");
      }
      if (payload.getDataType() == null) {
        return HttpResult.error("字典项的定义，必需指定数据类型。");
      }
    } else {
      payload.setCode(null);
      payload.setDataType(null);
    }

    DictDefineEntity entity = DictDefineEntity.create()
      .setDefineId(defineManager.nextId())
      .setParentId(payload.getParentId())
      .setAppId(payload.getAppId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setScope(payload.getScope())
      .setType(payload.getType())
      .setDataType(payload.getDataType())
      .setSort(payload.getSort())
      .setExtFields(payload.getExtFields())
      .setRemark(payload.getRemark());
    defineManager.insertOne(entity);

    DictDefineVo model = modelMapper.map(entity, DictDefineVo.class);
    return HttpResult.okBody(model);
  }

}
