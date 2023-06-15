/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
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
