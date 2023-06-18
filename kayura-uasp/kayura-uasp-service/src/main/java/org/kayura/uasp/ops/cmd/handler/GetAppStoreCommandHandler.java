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

package org.kayura.uasp.ops.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.applibrary.AppStoreVo;
import org.kayura.uasp.ops.cmd.GetAppStoreCommand;
import org.kayura.uasp.ops.entity.AppStoreEntity;
import org.kayura.uasp.ops.manage.AppStoreManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class GetAppStoreCommandHandler implements CommandHandler<GetAppStoreCommand, HttpResult> {

  private final ModelMapper modelMapper;
  private final AppStoreManager appStoreManager;

  public GetAppStoreCommandHandler(ModelMapper modelMapper,
                                   AppStoreManager appStoreManager) {
    this.modelMapper = modelMapper;
    this.appStoreManager = appStoreManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetAppStoreCommand command) {

    String releaseId = command.getReleaseId();

    AppStoreEntity entity = appStoreManager.selectById(releaseId);
    if (entity == null) {
      return HttpResult.error("要获取的数据不存在。");
    }
    AppStoreVo model = modelMapper.map(entity, AppStoreVo.class);
    return HttpResult.okBody(model);
  }

}
