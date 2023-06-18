/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
