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
