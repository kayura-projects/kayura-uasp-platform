/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
