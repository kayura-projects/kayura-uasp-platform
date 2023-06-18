/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
