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