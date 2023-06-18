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
