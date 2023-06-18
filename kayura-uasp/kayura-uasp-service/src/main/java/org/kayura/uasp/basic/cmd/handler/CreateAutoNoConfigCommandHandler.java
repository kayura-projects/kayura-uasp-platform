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
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.autono.AutoNoPayload;
import org.kayura.uasp.autono.AutoNoVo;
import org.kayura.uasp.basic.cmd.CreateAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.entity.AutoNoDefineEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.basic.manage.AutoNoDefineManager;
import org.kayura.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateAutoNoConfigCommandHandler implements CommandHandler<CreateAutoNoConfigCommand, HttpResult> {

  private final AutoNoDefineManager defineManager;
  private final AutoNoConfigManager configManager;
  private final ModelMapper modelMapper;

  public CreateAutoNoConfigCommandHandler(AutoNoDefineManager defineManager,
                                          AutoNoConfigManager configManager,
                                          ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.configManager = configManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateAutoNoConfigCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AutoNoPayload payload = command.getPayload();

    if (loginUser.hasTenantUser()) {
      payload.setAppId(loginUser.getAppId());
      payload.setTenantId(loginUser.getTenantId());
    }

    if (defineManager.selectCount(w ->
      w.eq(AutoNoDefineEntity::getCode, payload.getCode())
    ) > 0) {
      return HttpResult.error("字典定义ID重复。");
    }

    AutoNoDefineEntity define = AutoNoDefineEntity.create()
      .setDefineId(defineManager.nextId())
      .setCode(payload.getCode())
      .setName(payload.getName())
      .setAppId(payload.getAppId());
    defineManager.insertOne(define);

    AutoNoConfigEntity config = AutoNoConfigEntity.create()
      .setConfigId(configManager.nextId())
      .setDefineId(define.getDefineId())
      .setExpression(payload.getExpression())
      .setIncValue(payload.getIncValue())
      .setCustomCycle(payload.getCustomCycle())
      .setCountLength(payload.getCountLength())
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setUpdaterId(loginUser.getUserId())
      .setUpdateTime(DateUtils.now())
      .setRemark(payload.getRemark());
    configManager.insertOne(config);

    AutoNoVo model = modelMapper.map(config, AutoNoVo.class);
    model.setHasCustom(Boolean.FALSE);
    return HttpResult.okBody(model);
  }

}
