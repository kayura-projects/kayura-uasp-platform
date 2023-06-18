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
