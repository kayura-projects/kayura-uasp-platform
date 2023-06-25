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

package org.kayura.uasp.organize;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class DepartPayload {

  @NotBlank(groups = Update.class)
  private String departId;
  private String parentId;
  private String companyId;
  private String code;
  @NotBlank
  private String name;
  @NotNull(groups = Update.class)
  private Integer sort;
  @NotNull(groups = Update.class)
  private DataStatus status;
  private String remark;
  private List<LeaderFrm> leaders;

  public String getDepartId() {
    return departId;
  }

  public DepartPayload setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public DepartPayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public DepartPayload setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public DepartPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public DepartPayload setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public DepartPayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public DepartPayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DepartPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<LeaderFrm> getLeaders() {
    return leaders;
  }

  public DepartPayload setLeaders(List<LeaderFrm> leaders) {
    this.leaders = leaders;
    return this;
  }
}
