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
