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

import java.util.Objects;

public class KeyValueT<VALUE> {

  private String key;
  private VALUE value;

  public String getKey() {
    return key;
  }

  public KeyValueT<VALUE> setKey(String key) {
    this.key = key;
    return this;
  }

  public VALUE getValue() {
    return value;
  }

  public KeyValueT<VALUE> setValue(VALUE value) {
    this.value = value;
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    KeyValueT<?> keyValueT = (KeyValueT<?>) o;
    if (!Objects.equals(key, keyValueT.key)) return false;
    return value.equals(keyValueT.value);
  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + value.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "KeyValueT{" + "key='" + key + '\'' + ", value=" + value + '}';
  }
}
