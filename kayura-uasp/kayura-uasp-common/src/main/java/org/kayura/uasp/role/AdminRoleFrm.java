package org.kayura.uasp.role;

import org.kayura.type.UsableStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class AdminRoleFrm {

  @NotBlank(groups = Update.class)
  private String roleId;
  private String code;
  @NotBlank
  private String name;
  private Integer sort;
  private String remark;
  private UsableStatus status;

  public static AdminRoleFrm create() {
    return new AdminRoleFrm();
  }

  public String getRoleId() {
    return roleId;
  }

  public AdminRoleFrm setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public AdminRoleFrm setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public AdminRoleFrm setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public AdminRoleFrm setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AdminRoleFrm setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public AdminRoleFrm setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }
}
