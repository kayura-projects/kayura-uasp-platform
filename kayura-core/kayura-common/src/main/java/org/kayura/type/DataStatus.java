/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
