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

package org.kayura.uasp.dev.entity;

import org.kayura.mybatis.annotation.mapper.Sort;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.func.ActionTypes;

@Table("uasp_module_action")
public class ModuleActionEntity {

  private String moduleId;
  private ActionTypes type;
  private String code;
  private String name;
  @Sort
  private Integer sort;

  public static ModuleActionEntity create() {
    return new ModuleActionEntity();
  }

  public String getModuleId() {
    return moduleId;
  }

  public ModuleActionEntity setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public ActionTypes getType() {
    return type;
  }

  public ModuleActionEntity setType(ActionTypes type) {
    this.type = type;
    return this;
  }

  public String getCode() {
    return code;
  }

  public ModuleActionEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public ModuleActionEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public ModuleActionEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }
}
