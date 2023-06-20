/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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