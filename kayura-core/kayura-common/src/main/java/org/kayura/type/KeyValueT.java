/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
