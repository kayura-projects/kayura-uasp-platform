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

import org.kayura.uasp.func.ModuleTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrivilegeModuleVo {

  private String moduleId;
  private String moduleName;
  private String icon;
  private ModuleTypes type;
  private List<PrivilegeModuleVo> children;
  private List<PrivilegeActionVo> actions;

  public static PrivilegeModuleVo create() {
    return new PrivilegeModuleVo();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PrivilegeModuleVo that = (PrivilegeModuleVo) o;
    return moduleId.equals(that.moduleId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(moduleId);
  }

  public String getModuleId() {
    return moduleId;
  }

  public PrivilegeModuleVo setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public String getModuleName() {
    return moduleName;
  }

  public PrivilegeModuleVo setModuleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  public ModuleTypes getType() {
    return type;
  }

  public PrivilegeModuleVo setType(ModuleTypes type) {
    this.type = type;
    return this;
  }

  public List<PrivilegeModuleVo> getChildren() {
    return children;
  }

  public PrivilegeModuleVo setChildren(List<PrivilegeModuleVo> children) {
    this.children = children;
    return this;
  }

  public PrivilegeModuleVo addChildren(PrivilegeModuleVo item) {

    if (this.children == null) {
      this.children = new ArrayList<>();
    }
    this.children.add(item);
    return this;
  }

  public List<PrivilegeActionVo> getActions() {
    return actions;
  }

  public PrivilegeModuleVo setActions(List<PrivilegeActionVo> actions) {
    this.actions = actions;
    return this;
  }

  public PrivilegeModuleVo addAction(PrivilegeActionVo action) {

    if (this.actions == null) {
      this.actions = new ArrayList<>();
    }
    this.actions.add(action);
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public PrivilegeModuleVo setIcon(String icon) {
    this.icon = icon;
    return this;
  }
}
