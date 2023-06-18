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
import org.kayura.uasp.basic.cmd.UpdateAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.entity.AutoNoDefineEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.basic.manage.AutoNoDefineManager;
import org.kayura.uasp.utils.UaspConstants;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class UpdateAutoNoConfigCommandHandler implements CommandHandler<UpdateAutoNoConfigCommand, HttpResult> {

  private final AutoNoDefineManager defineManager;
  private final AutoNoConfigManager configManager;
  private final ModelMapper modelMapper;

  public UpdateAutoNoConfigCommandHandler(AutoNoDefineManager defineManager,
                                          AutoNoConfigManager configManager,
                                          ModelMapper modelMapper) {
    this.defineManager = defineManager;
    this.configManager = configManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(UpdateAutoNoConfigCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AutoNoPayload payload = command.getPayload();

    if (loginUser.hasTenantUser()) {
      payload.setAppId(loginUser.getAppId());
      payload.setTenantId(loginUser.getTenantId());
    }

    AutoNoConfigEntity entity = configManager.selectById(payload.getConfigId());
    if (entity == null) {
      return HttpResult.error("更新的数据不存在。");
    }

    String configId = entity.getConfigId();
    AutoNoVo result;

    // 如果实体没有 tenantId 值，又传入的 tenantId 值。
    if (StringUtils.isBlank(entity.getTenantId()) &&
      !UaspConstants.GLOBAL.equalsIgnoreCase(payload.getTenantId())) {

      // 表示要创建租户的自定义配置。
      configId = configManager.nextId();
      AutoNoConfigEntity config = AutoNoConfigEntity.create()
        .setConfigId(configId)
        .setTenantId(payload.getTenantId())
        .setDefineId(entity.getDefineId())
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

      result = this.findAutoNoById(configId).setHasCustom(Boolean.TRUE);
    } else {

      // 全局配置，可以修改定义编号与名称
      if (UaspConstants.GLOBAL.equalsIgnoreCase(payload.getTenantId())) {
        AutoNoDefineEntity defineEntity = defineManager.selectById(entity.getDefineId());
        defineEntity.setCode(payload.getCode());
        defineEntity.setName(payload.getName());
        defineManager.updateById(defineEntity.getDefineId(), defineEntity);
      }

      entity.setExpression(payload.getExpression());
      entity.setIncValue(payload.getIncValue());
      entity.setCustomCycle(payload.getCustomCycle());
      entity.setCountLength(payload.getCountLength());
      entity.setRemark(payload.getRemark());
      entity.setUpdaterId(loginUser.getUserId());
      entity.setUpdateTime(LocalDateTime.now());
      configManager.updateById(entity.getConfigId(), entity);

      result = this.findAutoNoById(configId);
      if (StringUtils.hasText(entity.getTenantId())) {
        result.setHasCustom(Boolean.TRUE);
      }
    }

    return HttpResult.okBody(result);
  }

  private AutoNoVo findAutoNoById(String id) {

    AutoNoVo autoNoVo = null;
    AutoNoConfigEntity entity = configManager.selectById(id);
    if (entity != null) {
      autoNoVo = modelMapper.map(entity, AutoNoVo.class);
    }
    return autoNoVo;
  }

}
