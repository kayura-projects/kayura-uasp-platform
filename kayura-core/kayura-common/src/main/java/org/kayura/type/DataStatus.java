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
