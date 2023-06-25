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
package org.kayura.uasp.rest.ops;

import org.kayura.cmd.CommandGateway;
import org.kayura.security.annotation.Secured;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.uasp.applibrary.AppStorePayload;
import org.kayura.uasp.applibrary.AppStoreQuery;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.dev.cmd.ChooseApplicCommand;
import org.kayura.uasp.ops.cmd.*;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.kayura.uasp.utils.SecurityConsts.*;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
@Secured(resource = UASP_APP_STORE)
public class AppStoreWebApi {

  private final CommandGateway commandGateway;

  public AppStoreWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/app-store/choose/applic")
  @Secured(actions = QUERY)
  public HttpResult chooseApplic(ChooseApplicCommand command) {
    return commandGateway.send(command.setType(ApplicTypes.APP).setOutput(OutputTypes.SELECT));
  }

  @PostMapping("/app-store/page")
  public HttpResult queryAppStorePage(QueryAppStoreCommand command,
                                      @RequestBody @Validated AppStoreQuery query,
                                      PageClause pageClause) {

    return commandGateway.send(command.setQuery(query).setPageClause(pageClause));
  }

  @GetMapping("/app-store/get")
  @Secured(actions = QUERY)
  public HttpResult findAppStoreById(GetAppStoreCommand command,
                                     @RequestParam("id") String releaseId) {

    return commandGateway.send(command.setReleaseId(releaseId));
  }

  @GetMapping("/app-store/download")
  @Secured(actions = QUERY)
  public void downloadAppStoreById(DownloadAppStoreCommand command,
                                   @RequestParam("id") String releaseId) {

    commandGateway.send(command.setReleaseId(releaseId));
  }

  @PostMapping("/app-store/create")
  @Secured(actions = CREATE)
  public HttpResult createAppStore(CreateAppStoreCommand command,
                                   @Validated(Create.class) @RequestBody AppStorePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/app-store/update")
  @Secured(actions = UPDATE)
  public HttpResult updateAppStore(UpdateAppStoreCommand command,
                                   @Validated(Update.class) @RequestBody AppStorePayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

  @PostMapping("/app-store/delete")
  @Secured(actions = DELETE)
  public HttpResult deleteAppStore(DeleteAppStoreCommand command,
                                   @RequestBody IdPayload payload) {

    return commandGateway.send(command.setPayload(payload));
  }

}
