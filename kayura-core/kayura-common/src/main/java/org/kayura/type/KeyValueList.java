/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
