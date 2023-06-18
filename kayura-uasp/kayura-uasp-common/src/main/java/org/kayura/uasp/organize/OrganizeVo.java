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

package org.kayura.uasp.organize;

public class OrganizeVo {

  private String id;
  private String code;
  private String name;
  private OrganizeTypes type;
  private Integer sort;

  public static OrganizeVo create() {
    return new OrganizeVo();
  }

  public String getId() {
    return id;
  }

  public OrganizeVo setId(String id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public OrganizeVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public OrganizeVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public OrganizeVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public OrganizeTypes getType() {
    return type;
  }

  public OrganizeVo setType(OrganizeTypes type) {
    this.type = type;
    return this;
  }
}
