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

package org.kayura.uasp.file;

import org.kayura.type.StringList;

public class UploadPayload {

  private String folderId;
  private String businessKey;
  private String category;
  private Boolean readonly;
  private Boolean encrypt;
  private StringList tags;
  private String fileName;

  public static UploadPayload create() {
    return new UploadPayload();
  }

  public String getFolderId() {
    return folderId;
  }

  public UploadPayload setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public UploadPayload setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public UploadPayload setCategory(String category) {
    this.category = category;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public UploadPayload setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypt() {
    return encrypt;
  }

  public UploadPayload setEncrypt(Boolean encrypt) {
    this.encrypt = encrypt;
    return this;
  }

  public StringList getTags() {
    return tags;
  }

  public UploadPayload setTags(StringList tags) {
    this.tags = tags;
    return this;
  }

  public String getFileName() {
    return fileName;
  }

  public UploadPayload setFileName(String fileName) {
    this.fileName = fileName;
    return this;
  }
}
