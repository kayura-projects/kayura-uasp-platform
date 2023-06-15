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
