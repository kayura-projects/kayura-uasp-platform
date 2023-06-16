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
