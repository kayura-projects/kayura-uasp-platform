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
import org.kayura.uasp.basic.cmd.*;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.feedback.ClosePayload;
import org.kayura.uasp.feedback.FeedbackPayload;
import org.kayura.uasp.feedback.FeedbackQuery;
import org.kayura.uasp.feedback.ReplyPayload;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.UaspConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_FEED_BACK)
public class FeedbackWebApi {

  private final CommandGateway commandGateway;

  public FeedbackWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/feed-back/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplics(ChooseApplicCommand command) {

    return commandGateway.send(command
      .setExclusionIds(UASP_APP_ID)
      .setOutput(OutputTypes.SELECT)
    );
  }

  // --- subject ---

  @PostMapping("/feed-back/page")
  @Secured(actions = QUERY)
  public HttpResult queryFeedbackPage(QueryFeedbackCommand command,
                                      @RequestBody FeedbackQuery query,
                                      PageClause pageClause) {

    return commandGateway.send(command
      .setQuery(query)
      .setPageClause(pageClause)
    );
  }

  @GetMapping("/feed-back/get")
  @Secured(actions = QUERY)
  public HttpResult findFeedbackById(GetFeedbackCommand command,
                                     @RequestParam("id") String id) {

    return commandGateway.send(command.setFeedbackId(id));
  }

  @PostMapping("/feed-back/create")
  @Secured(actions = CREATE)
  public HttpResult createSubject(CreateFeedbackCommand command,
                                  @RequestBody @Validated(Create.class) FeedbackPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/feed-back/close")
  public HttpResult updateSubject(CloseFeedbackCommand command,
                                  @RequestBody @Validated ClosePayload payload) {

    return commandGateway.send(command
      .setPostId(payload.getPostId())
      .setSolved(payload.getSolved())
    );
  }

  @PostMapping("/feed-back/delete")
  public HttpResult deleteSubject(DeleteFeedbackCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command
      .setDeleteType(DeleteFeedbackCommand.FEED_BACK)
      .setPayload(payload)
    );
  }

  // --- reply ---

  @PostMapping("/feed-back/reply/create")
  public HttpResult createReply(CreateFeedbackCommand command,
                                @RequestBody @Validated ReplyPayload payload) {

    return commandGateway.send(command.setPayload(FeedbackPayload.of(payload)));
  }

  @PostMapping("/feed-back/reply/delete")
  public HttpResult deleteReply(DeleteFeedbackCommand command, @RequestBody IdPayload payload) {

    return commandGateway.send(command
      .setDeleteType(DeleteFeedbackCommand.REPLY)
      .setPayload(payload)
    );
  }

}
