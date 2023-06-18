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
