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
