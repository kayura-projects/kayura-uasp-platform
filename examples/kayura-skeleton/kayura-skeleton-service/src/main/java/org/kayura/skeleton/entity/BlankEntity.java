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

package org.kayura.skeleton.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

@Table("skeleton_blank")
public class BlankEntity {

  @Id
  private String id;
  private String code;
  private String name;
  private String creatorId;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private DataStatus status;

  public String getId() {
    return id;
  }

  public BlankEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public BlankEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public BlankEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public BlankEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public BlankEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public BlankEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public BlankEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public BlankEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
