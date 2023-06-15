package org.kayura.uasp.workflow;

import org.kayura.type.EnumValue;

public enum CommentTypes implements EnumValue {

  Start("Start", "开始"),
  Complete("Complete", "完成的"),
  Todo("Todo", "待办的"),
  Not("Not", "未到达");

  private final String value;
  private final String name;

  CommentTypes(final String value, String name) {
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
