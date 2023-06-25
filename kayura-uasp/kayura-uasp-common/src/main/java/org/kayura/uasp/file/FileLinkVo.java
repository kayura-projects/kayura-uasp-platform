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
