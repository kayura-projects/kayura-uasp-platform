/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
