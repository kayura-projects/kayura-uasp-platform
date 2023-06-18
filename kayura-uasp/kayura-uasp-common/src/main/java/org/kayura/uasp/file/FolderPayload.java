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

import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class FolderPayload {

  @NotBlank(groups = Update.class)
  private String folderId;
  private String tenantId;
  private String parentId;
  private String name;
  @NotBlank(groups = Create.class)
  private OwnerTypes ownerType;
  private String ownerId;

  public String getTenantId() {
    return tenantId;
  }

  public FolderPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public FolderPayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public OwnerTypes getOwnerType() {
    return ownerType;
  }

  public FolderPayload setOwnerType(OwnerTypes ownerType) {
    this.ownerType = ownerType;
    return this;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public FolderPayload setOwnerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  public String getName() {
    return name;
  }

  public FolderPayload setName(String name) {
    this.name = name;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public FolderPayload setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }
}
