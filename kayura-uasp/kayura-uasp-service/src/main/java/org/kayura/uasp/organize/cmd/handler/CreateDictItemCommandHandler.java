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
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.uasp.dict.DictItemPayload;
import org.kayura.uasp.dict.DictItemVo;
import org.kayura.uasp.organize.cmd.CreateDictItemCommand;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateDictItemCommandHandler implements CommandHandler<CreateDictItemCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final DictItemManager itemManager;

  public CreateDictItemCommandHandler(ModelMapper modelMapper,
                                      DictItemManager itemManager) {
    this.modelMapper = modelMapper;
    this.itemManager = itemManager;
  }

  @Transactional
  public HttpResult execute(CreateDictItemCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DictItemPayload payload = command.getPayload();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      payload.setTenantId(loginUser.getTenantId());
    }

    if (StringUtils.hasText(payload.getCode()) &&
      itemManager.selectCount(w -> {
        w.eq(DictItemEntity::getDefineId, payload.getDefineId());
        w.eq(DictItemEntity::getCode, payload.getCode());
      }) > 0) {
      return HttpResult.error("编号已经存在。");
    }

    if (StringUtils.hasText(payload.getName()) &&
      itemManager.selectCount(w -> {
        w.eq(DictItemEntity::getDefineId, payload.getDefineId());
        w.eq(DictItemEntity::getName, payload.getName());
      }) > 0) {
      return HttpResult.error("名称已经存在。");
    }

    // 取得上级定义Id
    if (StringUtils.hasText(payload.getParentId())) {
      DictItemEntity parent = itemManager.selectOne(w -> {
        w.select(DictItemEntity::getDefineId);
        w.eq(DictItemEntity::getItemId, payload.getParentId());
      });
      if (parent != null) {
        payload.setDefineId(parent.getDefineId());
      }
    }

    DictItemEntity entity = DictItemEntity.create()
      .setItemId(itemManager.nextId())
      .setDefineId(payload.getDefineId())
      .setParentId(payload.getParentId())
      .setTenantId(payload.getTenantId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setSort(payload.getSort())
      .setStatus(payload.getStatus())
      .setExtField1(payload.getExtField1())
      .setExtField2(payload.getExtField2())
      .setExtField3(payload.getExtField3())
      .setExtField4(payload.getExtField4())
      .setExtField5(payload.getExtField5());
    itemManager.insertOne(entity);

    DictItemVo model = modelMapper.map(entity, DictItemVo.class);
    return HttpResult.okBody(model);
  }

}
