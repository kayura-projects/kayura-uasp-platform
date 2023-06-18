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

package org.kayura.uasp.func;

import org.kayura.type.CodeName;
import org.kayura.type.UsableStatus;

import java.util.List;

public class ModuleVo {

  private String moduleId;
  private String parentId;
  private String parentName;
  private ModuleTypes type;
  private String code;
  private String name;
  private String icon;
  private String url;
  private String target;
  private Integer sort;
  private UsageTypes usage;
  private UsableStatus status;
  private String remark;
  private List<CodeName> actions;

  public String getTypeName() {
    return this.type != null ? this.type.getName() : null;
  }

  public String getUsageName() {
    return this.usage != null ? this.usage.getName() : null;
  }

  public String getStatusName() {
    return this.status != null ? this.status.getName() : null;
  }

  public static ModuleVo create() {
    return new ModuleVo();
  }

  public String getModuleId() {
    return moduleId;
  }

  public ModuleVo setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ModuleVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public ModuleVo setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public ModuleTypes getType() {
    return type;
  }

  public ModuleVo setType(ModuleTypes type) {
    this.type = type;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ModuleVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ModuleVo setName(String name) {
    this.name = name;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public ModuleVo setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ModuleVo setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public ModuleVo setTarget(String target) {
    this.target = target;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ModuleVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsageTypes getUsage() {
    return usage;
  }

  public ModuleVo setUsage(UsageTypes usage) {
    this.usage = usage;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public ModuleVo setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ModuleVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<CodeName> getActions() {
    return actions;
  }

  public ModuleVo setActions(List<CodeName> actions) {
    this.actions = actions;
    return this;
  }
}
