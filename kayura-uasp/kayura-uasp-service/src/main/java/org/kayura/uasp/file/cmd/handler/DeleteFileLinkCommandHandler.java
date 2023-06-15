package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.file.cmd.DeleteFileLinkCommand;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DeleteFileLinkCommandHandler implements CommandHandler<DeleteFileLinkCommand, HttpResult> {

  private final FileLinkManager linkManager;

  public DeleteFileLinkCommandHandler(FileLinkManager linkManager) {
    this.linkManager = linkManager;
  }

  @Transactional
  public HttpResult execute(DeleteFileLinkCommand command) {

    LoginUser loginUser = command.getLoginUser();
    IdPayload payload = command.getPayload();

    if (StringUtils.hasText(payload.getId())) {
      linkManager.deleteByWhere(w -> {
        w.eq(FileLinkEntity::getLinkId, payload.getId());
      });
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      linkManager.deleteByWhere(w -> {
        w.in(FileLinkEntity::getLinkId, payload.getIds());
      });
    }

    return HttpResult.ok();
  }

}
