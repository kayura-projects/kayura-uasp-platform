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