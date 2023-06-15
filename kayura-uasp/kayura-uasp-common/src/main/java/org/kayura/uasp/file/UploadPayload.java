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
