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
