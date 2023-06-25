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
