package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.IdentityVo;
import org.kayura.uasp.organize.cmd.GetIdentityCommand;
import org.kayura.uasp.organize.entity.IdentityEntity;
import org.kayura.uasp.organize.manage.IdentityManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetIdentityCommandHandler implements CommandHandler<GetIdentityCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final IdentityManager identityManager;

  public GetIdentityCommandHandler(ModelMapper modelMapper,
                                   IdentityManager identityManager) {
    this.modelMapper = modelMapper;
    this.identityManager = identityManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetIdentityCommand command) {

    String identityId = command.getIdentityId();

    IdentityEntity entity = identityManager.selectById(identityId);
    if (entity == null) {
      return HttpResult.error("要获取的数据不存在。");
    }

    IdentityVo model = modelMapper.map(entity, IdentityVo.class);
    return HttpResult.okBody(model);
  }

}
