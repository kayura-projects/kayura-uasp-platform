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

package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.UserEntity;

import java.time.LocalDateTime;

/**
 * 岗位(uasp_position) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_position")
public class PositionEntity {

  /** 岗位Id */
  @Id
  private String positionId;
  /** 部门ID */
  @ForeignKey(entity = DepartEntity.class, alias = "dp")
  private String departId;
  @RefColumn(from = "dp", value = "name_")
  private String departName;
  @RefColumn(from = "dp")
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String companyId;
  @RefColumn(from = "cc", value = "tenant_id_")
  private String tenantId;
  /** 编码 */
  private String code;
  /** 名称 */
  private String name;
  /** 排序码 */
  @Sort(value = 1)
  private Integer sort;
  /** 级别:0-N 等级由高至低 */
  @Sort(value = 0)
  private Integer level;
  /** 创建人ID */
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 状态:D草搞,V可用,I禁用; */
  private DataStatus status;
  /** 备注 */
  private String remark;

  public static PositionEntity create() {
    return new PositionEntity();
  }

  public String getPositionId() {
    return positionId;
  }

  public PositionEntity setPositionId(String positionId) {
    this.positionId = positionId;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public PositionEntity setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public PositionEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public PositionEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public PositionEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public PositionEntity setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public PositionEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public PositionEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public PositionEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public PositionEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public PositionEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public PositionEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public PositionEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public PositionEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public PositionEntity setDepartName(String departName) {
    this.departName = departName;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public PositionEntity setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public PositionEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }
}