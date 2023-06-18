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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 键值集合类型
 *
 * @author 夏亮（liangxia@live.com）
 */
public class KeyValueList extends ArrayList<KeyValue> {

  public static KeyValueList fromMap(Map<String, String> map) {

    KeyValueList list = new KeyValueList();
    for (Map.Entry<String, String> e : map.entrySet()) {
      list.add(e.getKey(), e.getValue());
    }
    return list;
  }

  public static Map<String, String> toMap(List<KeyValue> kvs) {

    Map<String, String> map = new HashMap<>();
    for (KeyValue kv : kvs) {
      if (!map.containsKey(kv.getKey())) {
        map.put(kv.getKey(), kv.getValue());
      }
    }
    return map;
  }

  public KeyValueList add(String key, String value) {
    KeyValue kv = find(key);
    if (kv == null) {
      kv = new KeyValue(key, value);
      this.add(kv);
    } else {
      kv.setValue(value);
    }
    return this;
  }

  public KeyValue find(String key) {
    for (KeyValue kv : this) {
      if (key.equalsIgnoreCase(kv.getKey())) {
        return kv;
      }
    }
    return null;
  }

}
