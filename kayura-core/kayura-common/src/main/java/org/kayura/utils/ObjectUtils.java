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

import java.lang.reflect.Array;
import java.time.temporal.TemporalAccessor;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class ObjectUtils {

  @SuppressWarnings("unchecked")
  public static <T> T[] cast(Object[] objs, Class<T> clazz) {
    int length = objs.length;
    if (length == 0) {
      return (T[]) new Object[0];
    }
    T[] newArr = (T[]) Array.newInstance(clazz, objs.length);
    for (int i = 0; i < length; i++) {
      newArr[i] = clazz.cast(objs[i]);
    }

    return newArr;
  }

  //---------------------------------------------------------------------
  // 对象类型判断
  //---------------------------------------------------------------------

  public static boolean isCollection(Object obj) {
    return obj instanceof Collection;
  }

  public static boolean isMap(Object obj) {
    return obj instanceof Map;
  }

  public static boolean isNumber(Object obj) {
    return obj instanceof Number;
  }

  public static boolean isBoolean(Object obj) {
    return obj instanceof Boolean;
  }

  public static boolean isEnum(Object obj) {
    return obj instanceof Enum;
  }

  public static boolean isDate(Object obj) {
    return obj instanceof Date || obj instanceof TemporalAccessor;
  }

  public static boolean isCharSequence(Object obj) {
    return obj instanceof CharSequence;
  }

  public static boolean isPrimitive(Object obj) {
    return obj != null && obj.getClass().isPrimitive();
  }

  public static boolean isWrapperOrPrimitive(Object obj) {
    return isPrimitive(obj) || isNumber(obj) || isCharSequence(obj) || isBoolean(obj);
  }

  public static boolean isArray(Object obj) {
    return obj != null && obj.getClass().isArray();
  }

  public static boolean isPrimitiveArray(Object obj) {
    return isArray(obj) && obj.getClass().getComponentType().isPrimitive();
  }
}