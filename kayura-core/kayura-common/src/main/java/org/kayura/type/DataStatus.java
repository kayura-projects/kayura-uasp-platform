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

/** 表示数据状态的枚举类。 */
public enum DataStatus implements EnumValue {

  /** 草稿状态，对应值为 "D"。 */
  Draft("D", "草稿"),

  /** 启用状态，对应值为 "V"。 */
  Valid("V", "启用"),

  /** 禁用状态，对应值为 "I"。 */
  Invalid("I", "禁用");

  private final String value;
  private final String name;

  /**
   * 枚举类构造函数。
   *
   * @param value 枚举值对应的字符串表示。
   * @param name  枚举值的名称。
   */
  DataStatus(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  /**
   * 获取枚举值的字符串表示。
   *
   * @return 枚举值的字符串表示。
   */
  @Override
  public String toString() {
    return this.value;
  }

  /**
   * 获取枚举值的名称。
   *
   * @return 枚举值的名称。
   */
  @Override
  public String getName() {
    return name;
  }
}
