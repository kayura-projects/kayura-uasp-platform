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

import org.jetbrains.annotations.NotNull;

/**
 * 键值类型
 *
 * @author 夏亮（liangxia@live.com）
 */
public class KeyValue extends KeyValueT<String> {

  public static KeyValue builder() {
    return new KeyValue();
  }

  public KeyValue() {
  }

  public KeyValue(@NotNull String key, @NotNull String value) {
    this.setKey(key);
    this.setValue(value);
  }

}
