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

public class UploadFile {

  private String businessKey;
  private String category;
  private Boolean readonly;
  private String folderId;
  private Boolean encrypt;
  private String hashCode;
  private String salt;
  private StringList tags;
  private long fileSize;
  private String contentType;
  private String fileName;
  private String postfix;
  private StoreTypes storeType;
  private String logicPath;
  private String uploaderId;
  private String uploaderName;

  public static UploadFile create() {
    return new UploadFile();
  }

  public static UploadFile of(UploadPayload payload) {
    return create()
      .setFolderId(payload.getFolderId())
      .setBusinessKey(payload.getBusinessKey())
      .setCategory(payload.getCategory())
      .setCategory(payload.getCategory())
      .setReadonly(payload.getReadonly())
      .setEncrypt(payload.getEncrypt())
      .setTags(payload.getTags())
      .setFileName(payload.getFileName());
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public UploadFile setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public UploadFile setCategory(String category) {
    this.category = category;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public UploadFile setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypt() {
    return encrypt;
  }

  public UploadFile setEncrypt(Boolean encrypt) {
    this.encrypt = encrypt;
    return this;
  }

  public String getHashCode() {
    return hashCode;
  }

  public UploadFile setHashCode(String hashCode) {
    this.hashCode = hashCode;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public UploadFile setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public StringList getTags() {
    return tags;
  }

  public UploadFile setTags(StringList tags) {
    this.tags = tags;
    return this;
  }

  public long getFileSize() {
    return fileSize;
  }

  public UploadFile setFileSize(long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public UploadFile setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public String getFileName() {
    return fileName;
  }

  public UploadFile setFileName(String fileName) {
    this.fileName = fileName;
    return this;
  }

  public String getPostfix() {
    return postfix;
  }

  public UploadFile setPostfix(String postfix) {
    this.postfix = postfix;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public UploadFile setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public String getLogicPath() {
    return logicPath;
  }

  public UploadFile setLogicPath(String logicPath) {
    this.logicPath = logicPath;
    return this;
  }

  public String getUploaderId() {
    return uploaderId;
  }

  public UploadFile setUploaderId(String uploaderId) {
    this.uploaderId = uploaderId;
    return this;
  }

  public String getUploaderName() {
    return uploaderName;
  }

  public UploadFile setUploaderName(String uploaderName) {
    this.uploaderName = uploaderName;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public UploadFile setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }
}
