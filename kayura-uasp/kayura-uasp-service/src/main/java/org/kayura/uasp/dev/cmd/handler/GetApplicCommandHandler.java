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
