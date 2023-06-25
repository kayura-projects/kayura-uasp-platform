/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.nextid.RandomIdGenerator;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.BuildSneakCommand;
import org.kayura.security.sneak.SneakHandler;
import org.kayura.security.sneak.SneakItem;
import org.kayura.uasp.auth.model.SneakPayload;
import org.springframework.stereotype.Component;

@Component
public class BuildSneakCommandHandler implements CommandHandler<BuildSneakCommand, HttpResult> {

  private final SneakHandler sneakHandler;

  public BuildSneakCommandHandler(SneakHandler sneakHandler) {
    this.sneakHandler = sneakHandler;
  }

  @Override
  public HttpResult execute(BuildSneakCommand command) {

    LoginUser loginUser = command.getLoginUser();
    SneakPayload payload = command.getPayload();

    String nextId = RandomIdGenerator.INSTANCE.nextId();
    sneakHandler.build(nextId, SneakItem.create()
      .setUser(payload.getUsername())
      .setCaller(loginUser.getUserId())
    );
    return HttpResult.okBody(nextId);
  }

}
