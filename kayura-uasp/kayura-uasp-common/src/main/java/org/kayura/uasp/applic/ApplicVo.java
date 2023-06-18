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
