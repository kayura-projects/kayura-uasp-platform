package org.kayura.uasp.workflow;

import org.kayura.type.EnumValue;

/** END 完成, NOW 当前，NOT 未到达 */
public enum FlowStatus implements EnumValue {

  END("END", "已完成"),
  NOW("NOW", "当前的"),
  NOT("NOT", "未到达");

  private final String value;
  private final String name;

  FlowStatus(final String value, String name) {
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
