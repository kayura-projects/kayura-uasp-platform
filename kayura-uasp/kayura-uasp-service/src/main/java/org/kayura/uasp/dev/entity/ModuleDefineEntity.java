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

package org.kayura.uasp.dev.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.func.ModuleTypes;
import org.kayura.uasp.func.UsageTypes;

import java.util.List;

@Table("uasp_module_define")
public class ModuleDefineEntity {

  @Id
  private String moduleId;
  private String appId;
  @ForeignKey(entity = ModuleDefineEntity.class, alias = "mdp", pkName = "module_id_")
  private String parentId;
  @RefColumn(from = "mdp", value = "name_")
  private String parentName;
  private ModuleTypes type;
  private String code;
  private String name;
  private String icon;
  private String url;
  private String target;
  @Sort
  private Integer sort;
  private UsageTypes usage;
  private UsableStatus status;
  private String remark;

  @Virtual
  private List<ModuleActionEntity> actions;

  public static ModuleDefineEntity create() {
    return new ModuleDefineEntity();
  }

  public String getModuleId() {
    return moduleId;
  }

  public ModuleDefineEntity setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public ModuleDefineEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ModuleDefineEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getParentName() {
    return parentName;
  }

  public ModuleDefineEntity setParentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  public ModuleTypes getType() {
    return type;
  }

  public ModuleDefineEntity setType(ModuleTypes type) {
    this.type = type;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ModuleDefineEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ModuleDefineEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public ModuleDefineEntity setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ModuleDefineEntity setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public ModuleDefineEntity setTarget(String target) {
    this.target = target;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ModuleDefineEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsageTypes getUsage() {
    return usage;
  }

  public ModuleDefineEntity setUsage(UsageTypes usage) {
    this.usage = usage;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public ModuleDefineEntity setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ModuleDefineEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public List<ModuleActionEntity> getActions() {
    return actions;
  }

  public ModuleDefineEntity setActions(List<ModuleActionEntity> actions) {
    this.actions = actions;
    return this;
  }
}
