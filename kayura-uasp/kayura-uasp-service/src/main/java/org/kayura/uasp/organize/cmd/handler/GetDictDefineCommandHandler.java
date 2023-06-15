package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.dict.DictDefineVo;
import org.kayura.uasp.organize.cmd.GetDictDefineCommand;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetDictDefineCommandHandler implements CommandHandler<GetDictDefineCommand, HttpResult> {

  private final DictDefineManager defineManager;
  private final ModelMapper modelMapper;

  public GetDictDefineCommandHandler(DictDefineManager defineManager,
                                     ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetDictDefineCommand command) {

    String defineId = command.getDefineId();

    DictDefineEntity entity = defineManager.selectById(defineId);
    if (entity == null) {
      return HttpResult.error("要获取的记录不存在。");
    }
    DictDefineVo model = modelMapper.map(entity, DictDefineVo.class);
    return HttpResult.okBody(model);
  }

}
