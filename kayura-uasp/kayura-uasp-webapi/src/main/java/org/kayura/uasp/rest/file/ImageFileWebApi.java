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
