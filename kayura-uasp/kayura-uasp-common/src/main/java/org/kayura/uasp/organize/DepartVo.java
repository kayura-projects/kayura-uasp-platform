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

package org.kayura.uasp.organize;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;
import java.util.List;

public class DepartVo {

  private String departId;
  private String parentId;
  private String parentName;
  private String companyId;
  private String companyName;
  private String code;
  private String name;
  private Integer sort;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private DataStatus status;
  private String remark;
  private List<LeaderVo> leaders;

  public static DepartVo create() {
    return new DepartVo();
  }

  public String getDepartId() {
    return departId;
  }

  public DepartVo setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DepartVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public DepartVo setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DepartVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DepartVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DepartVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public DepartVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public DepartVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public DepartVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public DepartVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public DepartVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DepartVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<LeaderVo> getLeaders() {
    return leaders;
  }

  public DepartVo setLeaders(List<LeaderVo> leaders) {
    this.leaders = leaders;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public void setCreatorName(String creatorName) {
    this.creatorName = creatorName;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public void setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
  }

  public String getParentName() {
    return parentName;
  }

  public DepartVo setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public DepartVo setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }
}
