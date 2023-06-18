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

package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;

import java.util.List;

public class ImageDownloadCommand extends Command {

  private String formula;
  private List<String> imageTypes;

  public String getFormula() {
    return formula;
  }

  public ImageDownloadCommand setFormula(String formula) {
    this.formula = formula;
    return this;
  }

  public List<String> getImageTypes() {
    return imageTypes;
  }

  public ImageDownloadCommand setImageTypes(List<String> imageTypes) {
    this.imageTypes = imageTypes;
    return this;
  }
}
