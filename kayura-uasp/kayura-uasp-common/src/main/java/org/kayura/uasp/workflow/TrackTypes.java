package org.kayura.uasp.workflow;

import org.kayura.type.EnumValue;

public enum TrackTypes implements EnumValue {

  MY("MY", "我的任务"),
  TODO("TODO", "所有任务"),
  END("END", "已办任务"),
  NOT("NOT", "未到达任务");

  private final String value;
  private final String name;

  TrackTypes(final String value, String name) {
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
