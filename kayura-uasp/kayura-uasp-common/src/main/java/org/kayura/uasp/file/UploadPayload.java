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
