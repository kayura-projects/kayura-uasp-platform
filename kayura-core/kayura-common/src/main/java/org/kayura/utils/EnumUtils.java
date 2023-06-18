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
