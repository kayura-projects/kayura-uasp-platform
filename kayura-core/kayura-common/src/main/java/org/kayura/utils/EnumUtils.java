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

package org.kayura.utils;

import org.kayura.type.EnumValue;

import java.util.stream.Stream;

public class EnumUtils {

  /**
   * 使用自定义枚举值获取枚举对象。
   *
   * @param type  枚举对象类型
   * @param value 枚举值
   * @param <E>   继承值 ValueName 接口的枚举
   * @return 返回该值对应的枚举对象。
   */
  public static <E extends EnumValue> E valueOf(Class<E> type, String value) {
    return Stream.of(type.getEnumConstants()).filter(a -> a.toString().equals(value)).findFirst().orElse(null);
  }

  public static boolean equals(EnumValue obj1, EnumValue obj2) {

    if (obj1 == obj2) {
      return true;
    }

    return obj1.getValue().equals(obj2.getValue());
  }

}
