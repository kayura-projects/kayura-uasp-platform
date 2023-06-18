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
