package org.kayura.uasp.file.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.file.MoveFilePayload;
import org.kayura.uasp.file.cmd.MoveFileLinkCommand;
import org.kayura.uasp.file.entity.FileLinkEntity;
import org.kayura.uasp.file.manage.FileLinkManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MoveFileLinkCommandHandler implements CommandHandler<MoveFileLinkCommand, HttpResult> {

  private final FileLinkManager linkManager;

  public MoveFileLinkCommandHandler(FileLinkManager linkManager) {
    this.linkManager = linkManager;
  }

  @Transactional
  public HttpResult execute(MoveFileLinkCommand command) {

    LoginUser loginUser = command.getLoginUser();
    MoveFilePayload payload = command.getPayload();

    linkManager.updateByWhere(w -> {
      w.set(FileLinkEntity::getFolderId, payload.getFolderId());
      w.in(FileLinkEntity::getLinkId, payload.getLinkIds());
    });

    return HttpResult.ok();
  }
}
