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
