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
