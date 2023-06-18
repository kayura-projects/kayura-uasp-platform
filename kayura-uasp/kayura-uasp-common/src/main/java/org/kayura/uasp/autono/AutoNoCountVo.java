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

package org.kayura.uasp.autono;

import java.util.List;

public class AutoNoCountVo {

  private String countId;
  private String cycleValue;
  private Integer countValue;
  private List<String> recycleNos;

  public static AutoNoCountVo create() {
    return new AutoNoCountVo();
  }

  public String getCycleValue() {
    return cycleValue;
  }

  public AutoNoCountVo setCycleValue(String cycleValue) {
    this.cycleValue = cycleValue;
    return this;
  }

  public Integer getCountValue() {
    return countValue;
  }

  public AutoNoCountVo setCountValue(Integer countValue) {
    this.countValue = countValue;
    return this;
  }

  public List<String> getRecycleNos() {
    return recycleNos;
  }

  public AutoNoCountVo setRecycleNos(List<String> recycleNos) {
    this.recycleNos = recycleNos;
    return this;
  }

  public String getCountId() {
    return countId;
  }

  public AutoNoCountVo setCountId(String countId) {
    this.countId = countId;
    return this;
  }
}
