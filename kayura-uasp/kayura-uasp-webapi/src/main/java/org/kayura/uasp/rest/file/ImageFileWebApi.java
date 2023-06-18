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
