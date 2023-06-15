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
