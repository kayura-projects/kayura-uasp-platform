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