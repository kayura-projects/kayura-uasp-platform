package org.kayura.uasp.ip;

import org.kayura.type.EnumValue;

/** IP类型:LOCAL本机,LAN局域网,WAN广域网 */
public enum IPTypes implements EnumValue {

  LOCAL("LOCAL", "本机"),
  LAN("LAN", "局域网"),
  WAN("WAN", "广域网");

  private final String value;
  private final String name;

  IPTypes(final String value, String name) {
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
