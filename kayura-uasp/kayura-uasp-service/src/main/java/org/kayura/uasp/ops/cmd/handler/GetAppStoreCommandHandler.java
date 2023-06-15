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
