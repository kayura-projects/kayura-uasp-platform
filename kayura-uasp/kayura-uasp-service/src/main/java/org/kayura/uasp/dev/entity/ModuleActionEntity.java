/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
