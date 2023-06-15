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
