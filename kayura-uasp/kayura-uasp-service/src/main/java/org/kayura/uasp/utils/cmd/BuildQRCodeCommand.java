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
package org.kayura.uasp.utils.cmd;

import org.kayura.cmd.ApiCommand;

public class BuildQRCodeCommand extends ApiCommand {

  private String content;
  private int width = 400;
  private int height = 400;
  private String format = "jpg";

  public String getContent() {
    return content;
  }

  public BuildQRCodeCommand setContent(String content) {
    this.content = content;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public BuildQRCodeCommand setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public BuildQRCodeCommand setHeight(int height) {
    this.height = height;
    return this;
  }

  public String getFormat() {
    return format;
  }

  public BuildQRCodeCommand setFormat(String format) {
    this.format = format;
    return this;
  }
}
