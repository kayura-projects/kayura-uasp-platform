/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.rest.file;

import org.kayura.cmd.CommandGateway;
import org.kayura.uasp.config.UploadSettings;
import org.kayura.uasp.file.cmd.ImageDownloadCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${kayura.uasp.api-url}")
public class ImageFileWebApi {

  private final CommandGateway commandGateway;
  private final UploadSettings uploadSettings;

  public ImageFileWebApi(CommandGateway commandGateway,
                         UploadSettings uploadSettings) {
    this.commandGateway = commandGateway;
    this.uploadSettings = uploadSettings;
  }

  @GetMapping("/image/{formula}")
  public void imageDownload(ImageDownloadCommand command,
                            @PathVariable String formula) {

    commandGateway.send(command.setImageTypes(uploadSettings.getImageTypes()).setFormula(formula));
  }

}
