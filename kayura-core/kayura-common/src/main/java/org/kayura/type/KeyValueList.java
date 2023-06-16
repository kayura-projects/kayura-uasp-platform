/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
