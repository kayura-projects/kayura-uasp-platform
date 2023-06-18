/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
