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

package org.kayura.uasp.workflow;

public class FlowLabel {

  private String code;
  private String name;
  private FlowStatus status = FlowStatus.NOT;
  private Integer sort;

  public static FlowLabel create() {
    return new FlowLabel();
  }

  public static FlowLabel clone(FlowLabel source) {
    return create().setCode(source.code).setName(source.name).setSort(source.sort);
  }

  public String getCode() {
    return code;
  }

  public FlowLabel setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public FlowLabel setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FlowLabel setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public FlowStatus getStatus() {
    return status;
  }

  public FlowLabel setStatus(FlowStatus status) {
    this.status = status;
    return this;
  }
}
