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

package org.kayura.uasp.activiti.model;

public class ProcInstCount {

  private String defineId;
  private Integer count;

  public static ProcInstCount builder() {
    return new ProcInstCount();
  }

  public String getDefineId() {
    return defineId;
  }

  public ProcInstCount setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public Integer getCount() {
    return count;
  }

  public ProcInstCount setCount(Integer count) {
    this.count = count;
    return this;
  }
}
