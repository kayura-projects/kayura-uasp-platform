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
