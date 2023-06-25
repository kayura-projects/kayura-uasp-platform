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
