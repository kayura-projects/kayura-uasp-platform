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