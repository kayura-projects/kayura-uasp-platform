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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.privilege.ModuleAction;
import org.kayura.uasp.privilege.PrivilegeTypes;

import java.util.List;

public class SavePrivilegeCommand extends Command {

  private String appId;
  private PrivilegeTypes type;
  private String linkId;
  private List<ModuleAction> privileges;

  public PrivilegeTypes getType() {
    return type;
  }

  public SavePrivilegeCommand setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getLinkId() {
    return linkId;
  }

  public SavePrivilegeCommand setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public List<ModuleAction> getPrivileges() {
    return privileges;
  }

  public SavePrivilegeCommand setPrivileges(List<ModuleAction> privileges) {
    this.privileges = privileges;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public SavePrivilegeCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}
