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

package org.kayura.uasp.dict;

public class DictField {

  private Integer index;
  private String displayName;
  private String fieldName;

  public Integer getIndex() {
    return index;
  }

  public DictField setIndex(Integer index) {
    this.index = index;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public DictField setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public DictField setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }
}
