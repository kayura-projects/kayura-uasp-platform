package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.autono.AutoNoVo;
import org.kayura.uasp.basic.cmd.GetAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetAutoNoConfigCommandHandler implements CommandHandler<GetAutoNoConfigCommand, HttpResult> {

  private final AutoNoConfigManager configManager;
  private final ModelMapper modelMapper;

  public GetAutoNoConfigCommandHandler(AutoNoConfigManager configManager,
                                       ModelMapper modelMapper) {
    this.configManager = configManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetAutoNoConfigCommand command) {

    String configId = command.getConfigId();

    AutoNoVo autoNoVo = null;
    AutoNoConfigEntity entity = configManager.selectById(configId);
    if (entity != null) {
      autoNoVo = modelMapper.map(entity, AutoNoVo.class);
    }
    return HttpResult.okBody(autoNoVo);
  }

}
