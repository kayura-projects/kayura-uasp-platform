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
import org.kayura.uasp.ops.cmd.DownloadAppStoreCommand;
import org.kayura.uasp.utils.cmd.BuildQRCodeCommand;
import org.kayura.utils.HttpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AppGetWebApi {

  private final CommandGateway commandGateway;

  public AppGetWebApi(CommandGateway commandGateway) {
    this.commandGateway = commandGateway;
  }

  @GetMapping("/app/{appCode}/download")
  public void downloadLastVersion(DownloadAppStoreCommand command,
                                  @PathVariable String appCode) {

    commandGateway.send(command.setAppCode(appCode));
  }

  @GetMapping("/app/{appCode}/qr")
  public void selectDownloadQRCode(BuildQRCodeCommand command,
                                   HttpServletRequest request,
                                   @PathVariable String appCode) {

    StringBuilder url = HttpUtils.buildRequestUrl(request);
    url.append("/app/").append(appCode).append("/download");

    commandGateway.send(command
      .setContent(url.toString())
      .setWidth(400)
      .setHeight(400)
      .setFormat("jpg")
    );
  }

}
