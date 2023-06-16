/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.uasp.dict.DictItemVo;
import org.kayura.uasp.organize.cmd.GetDictItemCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetDictItemCommandHandler implements CommandHandler<GetDictItemCommand, HttpResult> {

  private final DictItemManager itemManager;
  private final ModelMapper modelMapper;

  public GetDictItemCommandHandler(DictItemManager itemManager,
                                   ModelMapper modelMapper) {
    this.itemManager = itemManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetDictItemCommand command) {

    String itemId = command.getItemId();

    DictItemEntity entity = itemManager.selectById(itemId);
    if (entity == null) {
      return HttpResult.error("要获取的数据不存在。");
    }
    DictItemVo model = modelMapper.map(entity, DictItemVo.class);
    return HttpResult.okBody(model);
  }

}
