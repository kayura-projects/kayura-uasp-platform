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

package org.kayura.uasp.file.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.file.OwnerTypes;

import java.time.LocalDateTime;

/**
 * 文件目录(uasp_file_folder) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_file_folder")
public class FileFolderEntity {

  /** 目录ID */
  @Id
  private String folderId;
  /** 上级ID */
  private String parentId;
  /** 租户ID */
  private String tenantId;
  /** 目录名称 */
  private String name;
  /** 所有类型:U个人;G群组;D部门;T租户共享;S系统共享; */
  private OwnerTypes ownerType;
  /** 所有者ID */
  private String ownerId;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改时间 */
  private LocalDateTime updateTime;

  public static FileFolderEntity create() {
    return new FileFolderEntity();
  }

  public String getFolderId() {
    return folderId;
  }

  public FileFolderEntity setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public FileFolderEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FileFolderEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getName() {
    return name;
  }

  public FileFolderEntity setName(String name) {
    this.name = name;
    return this;
  }

  public OwnerTypes getOwnerType() {
    return ownerType;
  }

  public FileFolderEntity setOwnerType(OwnerTypes ownerType) {
    this.ownerType = ownerType;
    return this;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public FileFolderEntity setOwnerId(String ownerId) {
    this.ownerId = ownerId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public FileFolderEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public FileFolderEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}