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

package org.kayura.mybatis.mapper.wrapper.support;

import org.kayura.mybatis.toolkit.StringPool;

import java.io.Serializable;

public class SharedString implements Serializable {

  private String stringValue;

  public SharedString() {
  }

  public SharedString(String stringValue) {
    this.stringValue = stringValue;
  }

  public static SharedString emptyString() {
    return new SharedString(StringPool.EMPTY);
  }

  public String getStringValue() {
    return stringValue;
  }

  public SharedString setStringValue(String stringValue) {
    this.stringValue = stringValue;
    return this;
  }
}
