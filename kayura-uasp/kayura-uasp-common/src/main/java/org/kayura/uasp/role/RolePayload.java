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

package org.kayura.uasp.role;

import org.kayura.type.UsableStatus;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class RolePayload {

  @NotBlank(groups = Update.class)
  private String roleId;
  private String appId;
  private String tenantId;
  private RoleTypes type;
  private String code;
  @NotBlank
  private String name;
  private Integer sort;
  private String remark;
  private UsableStatus status;

  public static RolePayload create() {
    return new RolePayload();
  }

  public String getRoleId() {
    return roleId;
  }

  public RolePayload setRoleId(String roleId) {
    this.roleId = roleId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public RolePayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public RolePayload setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public RolePayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public RolePayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public RolePayload setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public RolePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public RolePayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public RoleTypes getType() {
    return type;
  }

  public RolePayload setType(RoleTypes type) {
    this.type = type;
    return this;
  }
}
