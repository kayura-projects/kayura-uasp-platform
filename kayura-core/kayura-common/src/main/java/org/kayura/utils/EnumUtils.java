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
