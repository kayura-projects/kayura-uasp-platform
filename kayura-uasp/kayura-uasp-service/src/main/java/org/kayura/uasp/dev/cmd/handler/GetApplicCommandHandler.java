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

package org.kayura.uasp.dev.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applic.ApplicVo;
import org.kayura.uasp.dev.cmd.GetApplicCommand;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetApplicCommandHandler implements CommandHandler<GetApplicCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final ApplicManager applicManager;

  public GetApplicCommandHandler(ModelMapper modelMapper,
                                 ApplicManager applicManager) {
    this.modelMapper = modelMapper;
    this.applicManager = applicManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetApplicCommand command) {

    String appId = command.getAppId();
    ApplicEntity entity = applicManager.selectById(appId);
    if (entity == null) {
      return HttpResult.error("应用记录不存在。");
    }
    ApplicVo model = modelMapper.map(entity, ApplicVo.class);
    return HttpResult.okBody(model);
  }

}
