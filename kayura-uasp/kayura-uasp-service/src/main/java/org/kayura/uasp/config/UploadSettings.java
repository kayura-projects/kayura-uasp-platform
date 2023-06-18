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

package org.kayura.uasp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "kayura.upload")
public class UploadSettings {

  private String alive;
  private List<String> imageTypes = Arrays.asList("jpg", "jpeg", "png", "gif");
  private List<String> editableTypes = Arrays.asList("doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt");
  private String storePath;
  private String tempPath;

  public String getAlive() {
    return alive;
  }

  public UploadSettings setAlive(String alive) {
    this.alive = alive;
    return this;
  }

  public List<String> getImageTypes() {
    return imageTypes;
  }

  public UploadSettings setImageTypes(List<String> imageTypes) {
    this.imageTypes = imageTypes;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public UploadSettings setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getTempPath() {
    return tempPath;
  }

  public UploadSettings setTempPath(String tempPath) {
    this.tempPath = tempPath;
    return this;
  }

  public List<String> getEditableTypes() {
    return editableTypes;
  }

  public UploadSettings setEditableTypes(List<String> editableTypes) {
    this.editableTypes = editableTypes;
    return this;
  }
}
