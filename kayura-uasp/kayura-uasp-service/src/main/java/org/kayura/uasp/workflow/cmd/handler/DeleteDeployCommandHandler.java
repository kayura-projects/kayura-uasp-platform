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
package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.workflow.cmd.DeleteDeployCommand;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.activiti.engine.RepositoryService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DeleteDeployCommandHandler implements CommandHandler<DeleteDeployCommand, HttpResult> {

  private final RepositoryService repositoryService;

  public DeleteDeployCommandHandler(RepositoryService repositoryService) {
    this.repositoryService = repositoryService;
  }

  @Transactional
  public HttpResult execute(DeleteDeployCommand command) {

    LoginUser loginUser = command.getLoginUser();
    boolean cascade = command.isCascade();
    IdPayload payload = Optional.ofNullable(command.getPayload()).orElse(IdPayload.create());
    String deployId = Optional.ofNullable(command.getDeployId()).orElse(payload.getId());

    if (StringUtils.hasText(deployId)) {
      repositoryService.deleteDeployment(deployId, cascade);
    } else if (CollectionUtils.isNotEmpty(payload.getIds())) {
      for (String id : payload.getIds()) {
        repositoryService.deleteDeployment(id, cascade);
      }
    }

    return HttpResult.ok();
  }
}
