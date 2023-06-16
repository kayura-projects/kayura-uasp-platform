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
