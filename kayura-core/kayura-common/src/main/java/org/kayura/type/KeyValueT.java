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
