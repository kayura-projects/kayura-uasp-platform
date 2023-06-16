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

package org.kayura.uasp.applic;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

public class ApplicVo {

  private String appId;
  private String code;
  private String name;
  private Integer level;
  private ApplicTypes type;
  private String url;
  private Integer sort;
  private Boolean needRelease;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private DataStatus status;
  private String remark;

  public static ApplicVo create() {
    return new ApplicVo();
  }

  public String getTypeName() {
    return this.type != null ? this.type.getName() : null;
  }

  public String getAppId() {
    return appId;
  }

  public ApplicVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ApplicVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ApplicVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ApplicVo setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ApplicVo setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ApplicVo setUrl(String url) {
    this.url = url;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ApplicVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public Boolean getNeedRelease() {
    return needRelease;
  }

  public ApplicVo setNeedRelease(Boolean needRelease) {
    this.needRelease = needRelease;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public ApplicVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public ApplicVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public ApplicVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public ApplicVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public ApplicVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public ApplicVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ApplicVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
