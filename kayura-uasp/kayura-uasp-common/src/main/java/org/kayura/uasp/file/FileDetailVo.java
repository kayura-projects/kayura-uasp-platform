/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.file;

import org.kayura.type.StringList;

import java.time.LocalDateTime;

public class FileDetailVo {

  private String linkId;
  private FileClassify classify;
  private String displayName;
  private String uploaderName;
  private String tenantName;
  private LocalDateTime uploadTime;
  private LocalDateTime lastModified;
  private String businessKey;
  private String category;
  private String folderPath;
  private Integer downloads;
  private StringList tags;

  private String fileId;
  private String contentType;
  private Long fileSize;
  private StoreTypes storeType;
  private String storePath;
  private Boolean readonly;
  private Boolean encrypted;
  private Boolean exists;

  public static FileDetailVo create() {
    return new FileDetailVo();
  }

  public String getLinkId() {
    return linkId;
  }

  public FileDetailVo setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public FileClassify getClassify() {
    return classify;
  }

  public FileDetailVo setClassify(FileClassify classify) {
    this.classify = classify;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FileDetailVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getUploaderName() {
    return uploaderName;
  }

  public FileDetailVo setUploaderName(String uploaderName) {
    this.uploaderName = uploaderName;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public FileDetailVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public LocalDateTime getUploadTime() {
    return uploadTime;
  }

  public FileDetailVo setUploadTime(LocalDateTime uploadTime) {
    this.uploadTime = uploadTime;
    return this;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public FileDetailVo setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public FileDetailVo setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FileDetailVo setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getFolderPath() {
    return folderPath;
  }

  public FileDetailVo setFolderPath(String folderPath) {
    this.folderPath = folderPath;
    return this;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public FileDetailVo setDownloads(Integer downloads) {
    this.downloads = downloads;
    return this;
  }

  public StringList getTags() {
    return tags;
  }

  public FileDetailVo setTags(StringList tags) {
    this.tags = tags;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public FileDetailVo setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public FileDetailVo setFileSize(Long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public FileDetailVo setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public FileDetailVo setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public FileDetailVo setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypted() {
    return encrypted;
  }

  public FileDetailVo setEncrypted(Boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }

  public String getFileId() {
    return fileId;
  }

  public FileDetailVo setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public Boolean getExists() {
    return exists;
  }

  public FileDetailVo setExists(Boolean exists) {
    this.exists = exists;
    return this;
  }
}
