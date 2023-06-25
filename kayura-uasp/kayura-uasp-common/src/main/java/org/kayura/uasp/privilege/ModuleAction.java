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

package org.kayura.uasp.privilege;

import org.kayura.type.StringList;

public class ModuleAction {

  private String moduleId;
  private StringList actions;

  public static ModuleAction create() {
    return new ModuleAction();
  }

  public String getModuleId() {
    return moduleId;
  }

  public ModuleAction setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public StringList getActions() {
    return actions;
  }

  public ModuleAction setActions(StringList actions) {
    this.actions = actions;
    return this;
  }
}
