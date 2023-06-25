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

public class DownloadFile {

  private String fileId;
  private String fileSize;
  private String contentType;
  private Boolean readonly;
  private Boolean encrypted;
  private String salt;
  private String businessKey;
  private String category;
  private String displayName;
  private String postfix;
  private StoreTypes storeType;
  private String storePath;
  private String uploaderId;
  private String uploaderName;

  public static DownloadFile create() {
    return new DownloadFile();
  }

  public String getFileId() {
    return fileId;
  }

  public DownloadFile setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public String getFileSize() {
    return fileSize;
  }

  public DownloadFile setFileSize(String fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public DownloadFile setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public DownloadFile setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypted() {
    return encrypted;
  }

  public DownloadFile setEncrypted(Boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public DownloadFile setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public DownloadFile setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public DownloadFile setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public DownloadFile setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPostfix() {
    return postfix;
  }

  public DownloadFile setPostfix(String postfix) {
    this.postfix = postfix;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public DownloadFile setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public DownloadFile setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getUploaderId() {
    return uploaderId;
  }

  public DownloadFile setUploaderId(String uploaderId) {
    this.uploaderId = uploaderId;
    return this;
  }

  public String getUploaderName() {
    return uploaderName;
  }

  public DownloadFile setUploaderName(String uploaderName) {
    this.uploaderName = uploaderName;
    return this;
  }
}
