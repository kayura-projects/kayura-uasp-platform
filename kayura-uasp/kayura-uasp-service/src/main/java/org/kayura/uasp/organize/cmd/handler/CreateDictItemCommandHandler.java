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
