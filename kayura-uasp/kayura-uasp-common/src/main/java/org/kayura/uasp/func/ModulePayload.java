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

package org.kayura.uasp.func;

import org.kayura.type.CodeName;
import org.kayura.type.UsableStatus;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ModulePayload {

  @NotNull(groups = Update.class)
  private String moduleId;
  @NotNull(groups = Create.class)
  private String appId;
  private String parentId;
  @NotNull(groups = Create.class)
  private ModuleTypes type;
  private String code;
  @NotBlank
  private String name;
  private String icon;
  private String url;
  private String target;
  private Integer sort;
  private UsageTypes usage;
  private UsableStatus status;
  private String remark;
  private List<CodeName> actions;

  public String getModuleId() {
    return moduleId;
  }

  public ModulePayload setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ModulePayload setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public ModuleTypes getType() {
    return type;
  }

  public ModulePayload setType(ModuleTypes type) {
    this.type = type;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ModulePayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ModulePayload setName(String name) {
    this.name = name;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public ModulePayload setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public ModulePayload setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getTarget() {
    return target;
  }

  public ModulePayload setTarget(String target) {
    this.target = target;
    return this;
  }

  public Integer getSort() {
    return sort != null ? sort : 0;
  }

  public ModulePayload setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public UsageTypes getUsage() {
    return usage != null ? usage : UsageTypes.Common;
  }

  public ModulePayload setUsage(UsageTypes usage) {
    this.usage = usage;
    return this;
  }

  public UsableStatus getStatus() {
    return status != null ? status : UsableStatus.Valid;
  }

  public ModulePayload setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ModulePayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public ModulePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public List<CodeName> getActions() {
    return actions;
  }

  public ModulePayload setActions(List<CodeName> actions) {
    this.actions = actions;
    return this;
  }
}
