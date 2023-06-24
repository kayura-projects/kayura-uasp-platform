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

package org.kayura.uasp.rest.basic;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.auth.cmd.*;
import org.kayura.uasp.basic.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.notice.NoticePayload;
import org.kayura.uasp.notice.NoticeQuery;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_NOTICE)
public class NoticeWebApi implements UaspConsts {

  private final CommandGateway commandGateway;

  public NoticeWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/notice/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplics(ChooseApplicCommand command) {

    return commandGateway.send(command
      .setExclusionIds(UASP_APP_ID)
      .setOutput(OutputTypes.SELECT)
    );
  }

  @GetMapping("/notice/choose/tenant")
  @Secured(actions = QUERY)
  public HttpResult chooseTenants(ChooseTenantCommand command, String appId) {

    return commandGateway.send(command
      .setAppId(appId)
      .setFormApp(true)
      .setOutput(OutputTypes.SELECT)
    );
  }

  /** Notice */

  @PostMapping("/notice/page")
  @Secured(actions = QUERY)
  public HttpResult queryNoticePage(QueryNoticeCommand command,
                                    @RequestBody NoticeQuery query,
                                    PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/notice/get")
  @Secured(actions = QUERY)
  public HttpResult findNoticeById(GetNoticeCommand command,
                                   @RequestParam("id") String noticeId) {

    return commandGateway.send(command.setNoticeId(noticeId));
  }

  @PostMapping("/notice/create")
  @Secured(actions = CREATE)
  public HttpResult createNotice(CreateNoticeCommand command,
                                 @RequestBody @Validated(Create.class) NoticePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/notice/update")
  @Secured(actions = UPDATE)
  public HttpResult updateNotice(UpdateNoticeCommand command,
                                 @RequestBody @Validated(Update.class) NoticePayload payload) {

    return commandGateway.send(command
      .setNoticeId(payload.getNoticeId())
      .setPayload(payload)
    );
  }

  @PostMapping("/notice/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteNotice(DeleteNoticeCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
