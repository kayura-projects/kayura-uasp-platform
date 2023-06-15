package org.kayura.uasp.file;

import org.kayura.type.EnumValue;

public enum StoreTypes implements EnumValue {

  NFS("NFS", "网络文件"),
  HDFS("HDFS", "Apache HDFS");

  private final String value;
  private final String name;

  StoreTypes(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public String getName() {
    return name;
  }
}
