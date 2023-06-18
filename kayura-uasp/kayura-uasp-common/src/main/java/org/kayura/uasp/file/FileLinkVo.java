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

import java.time.LocalDateTime;

public class FileLinkVo {

  private String linkId;
  private String fileId;
  private Long fileSize;
  private String contentType;
  private FileClassify classify;
  private Boolean readonly;
  private Boolean encrypted;
  private String salt;
  private StoreTypes storeType;
  private String storePath;
  private String folderId;
  private String tenantId;
  private String businessKey;
  private String category;
  private String displayName;
  private String postfix;
  private String uploaderId;
  private String uploaderName;
  private LocalDateTime uploadTime;
  private Integer sort;
  private StringList tags;
  private Integer downloads;
  private Boolean exists;

  private String tenantName;
  private LocalDateTime lastModified;
  private String folderPath;

  public static FileLinkVo create() {
    return new FileLinkVo();
  }

  public FileLinkVo clearSensitive() {
    this.setSalt(null);
    this.setStorePath(null);
    this.setTenantId(null);
    this.setEncrypted(null);
    this.setBusinessKey(null);
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public FileLinkVo setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public String getFileId() {
    return fileId;
  }

  public FileLinkVo setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public FileLinkVo setFileSize(Long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public FileLinkVo setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public FileClassify getClassify() {
    return classify;
  }

  public FileLinkVo setClassify(FileClassify classify) {
    this.classify = classify;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public FileLinkVo setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypted() {
    return encrypted;
  }

  public FileLinkVo setEncrypted(Boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public FileLinkVo setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public FileLinkVo setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public FileLinkVo setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public FileLinkVo setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FileLinkVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public FileLinkVo setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FileLinkVo setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FileLinkVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPostfix() {
    return postfix;
  }

  public FileLinkVo setPostfix(String postfix) {
    this.postfix = postfix;
    return this;
  }

  public String getUploaderId() {
    return uploaderId;
  }

  public FileLinkVo setUploaderId(String uploaderId) {
    this.uploaderId = uploaderId;
    return this;
  }

  public String getUploaderName() {
    return uploaderName;
  }

  public FileLinkVo setUploaderName(String uploaderName) {
    this.uploaderName = uploaderName;
    return this;
  }

  public LocalDateTime getUploadTime() {
    return uploadTime;
  }

  public FileLinkVo setUploadTime(LocalDateTime uploadTime) {
    this.uploadTime = uploadTime;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FileLinkVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public StringList getTags() {
    return tags;
  }

  public FileLinkVo setTags(StringList tags) {
    this.tags = tags;
    return this;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public FileLinkVo setDownloads(Integer downloads) {
    this.downloads = downloads;
    return this;
  }

  public Boolean getExists() {
    return exists;
  }

  public FileLinkVo setExists(Boolean exists) {
    this.exists = exists;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public FileLinkVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public FileLinkVo setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  public String getFolderPath() {
    return folderPath;
  }

  public FileLinkVo setFolderPath(String folderPath) {
    this.folderPath = folderPath;
    return this;
  }
}
