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
