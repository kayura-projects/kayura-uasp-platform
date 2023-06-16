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

package org.kayura.uasp.privilege;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserPrivilegePayload {

  private String appId;
  @NotBlank
  private String userId;
  private List<ModuleAction> privileges;

  public String getUserId() {
    return userId;
  }

  public UserPrivilegePayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public UserPrivilegePayload setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public UserPrivilegePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
