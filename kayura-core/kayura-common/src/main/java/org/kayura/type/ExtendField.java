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

package org.kayura.type;

public class ExtendField {

  private String key;
  private String value;
  private String remark;

  public static ExtendField create() {
    return new ExtendField();
  }

  public static ExtendField clone(ExtendField source) {
    return create().setKey(source.key).setValue(source.value).setRemark(source.remark);
  }

  public String getKey() {
    return key;
  }

  public ExtendField setKey(String key) {
    this.key = key;
    return this;
  }

  public String getValue() {
    return value;
  }

  public ExtendField setValue(String value) {
    this.value = value;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ExtendField setRemark(String remark) {
    this.remark = remark;
    return this;
  }

}
