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

package org.kayura.uasp.dev.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.auth.entity.UserEntity;

import java.time.LocalDateTime;

/**
 * 应用系统(uasp_applic) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_applic")
public class ApplicEntity {

  /** 应用ID */
  @Id
  @DisplayName("应用ID")
  private String appId;

  /** 系统代码:UASP、其它; */
  @Unique
  @DisplayName("系统代码")
  private String code;

  /** 系统名称 */
  @Unique
  @DisplayName("系统名称")
  private String name;

  /** 系统级别 */
  @DisplayName("系统级别")
  private Integer level;

  /** 系统类型:PC,WX,APP */
  @DisplayName("系统类型")
  private ApplicTypes type;

  /** 入口URL */
  @DisplayName("入口URL")
  private String url;

  /** 排序码 */
  @Sort
  @DisplayName("排序码")
  private Integer sort;

  /** 需要发布下载 */
  @DisplayName("需要发布下载")
  private Boolean needRelease;

  /** 创建人ID */
  @DisplayName("创建人ID")
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @DisplayName("创建人")
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;

  /** 创建时间 */
  @DisplayName("创建时间")
  private LocalDateTime createTime;

  /** 修改人ID */
  @DisplayName("修改人ID")
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @DisplayName("修改人")
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;

  /** 修改时间 */
  @DisplayName("修改时间")
  private LocalDateTime updateTime;

  /** 状态:D草搞,V可用,I禁用; */
  @DisplayName("状态")
  private DataStatus status;

  /** 备注 */
  @DisplayName("备注")
  private String remark;

  public static ApplicEntity create() {
    return new ApplicEntity();
  }

  public String getAppId() {
    return appId;
  }

  public ApplicEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ApplicEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ApplicEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ApplicEntity setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ApplicEntity setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ApplicEntity setUrl(String url) {
    this.url = url;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ApplicEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public Boolean getNeedRelease() {
    return needRelease;
  }

  public ApplicEntity setNeedRelease(Boolean needRelease) {
    this.needRelease = needRelease;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public ApplicEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public ApplicEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public ApplicEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public ApplicEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public ApplicEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public ApplicEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public ApplicEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ApplicEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}