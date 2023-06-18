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

package org.kayura.uasp.basic.model;

public class NewNoSettings {

  private String defineId;
  private String countId;
  private Integer incValue;
  private Integer countValue;
  private Integer countLength;
  private String customCycle;
  private String expression;

  public static NewNoSettings create() {
    return new NewNoSettings();
  }

  public Integer getIncValue() {
    return incValue;
  }

  public NewNoSettings setIncValue(Integer incValue) {
    this.incValue = incValue;
    return this;
  }

  public Integer getCountValue() {
    return countValue;
  }

  public NewNoSettings setCountValue(Integer countValue) {
    this.countValue = countValue;
    return this;
  }

  public String getExpression() {
    return expression;
  }

  public NewNoSettings setExpression(String expression) {
    this.expression = expression;
    return this;
  }

  public Integer getCountLength() {
    return countLength;
  }

  public NewNoSettings setCountLength(Integer countLength) {
    this.countLength = countLength;
    return this;
  }

  public String getCountId() {
    return countId;
  }

  public NewNoSettings setCountId(String countId) {
    this.countId = countId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public NewNoSettings setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getCustomCycle() {
    return customCycle;
  }

  public NewNoSettings setCustomCycle(String customCycle) {
    this.customCycle = customCycle;
    return this;
  }
}
