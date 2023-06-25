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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public final class ReflectUtils {

  public static Field[] getFields(Class<?> clazz) {
    return getFields(clazz, null);
  }

  public static Field[] getFields(Class<?> clazz, Predicate<Field> fieldFilter) {
    List<Field> fields = new ArrayList<>(32);
    while (Object.class != clazz && clazz != null) {
      // 获得该类的所有声明的字段，即包括public、private和protected，但是不包括父类的申明字段，
      // getFields：获得某个类的所有的公共（public）的字段，包括父类中的字段
      for (Field field : clazz.getDeclaredFields()) {
        if (fieldFilter != null && !fieldFilter.test(field)) {
          continue;
        }
        fields.add(field);
      }
      clazz = clazz.getSuperclass();
    }
    return fields.toArray(new Field[0]);
  }

  public static Field getField(Class<?> clazz, String name) {
    return getField(clazz, name, null);
  }

  public static Field getField(Class<?> clazz, String name, Class<?> type) {

    Assert.notNull(clazz, "clazz不能为空！");

    while (clazz != Object.class && clazz != null) {
      for (Field field : clazz.getDeclaredFields()) {
        if ((name == null || name.equals(field.getName())) && (type == null || type.equals(field.getType()))) {
          return field;
        }
      }
      clazz = clazz.getSuperclass();
    }
    if (clazz != null) {
      throw new IllegalStateException(clazz.getName() + "." + name + "字段不存在！");
    }
    return null;
  }

  public static Object getFieldValue(Field field, Object target) {
    makeAccessible(field, target);
    try {
      return field.get(target);
    } catch (Exception e) {
      throw new IllegalStateException("获取field值错误！");
    }
  }

  public static Object getFieldValue(Object obj, String fieldName) {
    Assert.notNull(obj, "obj不能为空!");
    if (ObjectUtils.isWrapperOrPrimitive(obj)) {
      return obj;
    }
    return getFieldValue(getField(obj.getClass(), fieldName), obj);
  }

  public static Object getValueByFieldPath(Object obj, String fieldPath) {
    String[] fieldNames = fieldPath.split("\\.");
    Object result = null;
    for (String fieldName : fieldNames) {
      result = getFieldValue(obj, fieldName);
      if (result == null) {
        return null;
      }
      obj = result;
    }
    return result;
  }

  public static void setFieldValue(Field field, Object target, Object value) {
    makeAccessible(field, target);
    try {
      field.set(target, value);
    } catch (Exception e) {
      throw new IllegalStateException("设置field值错误！");
    }
  }

  public static void makeAccessible(Field field, Object target) {
    if ((!Modifier.isPublic(field.getModifiers()) || !Modifier.isPublic(field.getDeclaringClass().getModifiers())
      || Modifier.isFinal(field.getModifiers())) && !field.canAccess(target)) {
      field.setAccessible(true);
    }
  }

  public static Object invokeMethod(Method method, Object target) {
    return invokeMethod(method, target, new Object[0]);
  }

  public static Object invokeMethod(Method method, Object target, Object... args) {
    try {
      makeAccessible(method, target);
      return method.invoke(target, args);
    } catch (Exception ex) {
      throw new IllegalStateException("执行方法失败！", ex);
    }
  }

  public static void makeAccessible(Method method, Object target) {
    if ((!Modifier.isPublic(method.getModifiers()) || !Modifier.isPublic(method.getDeclaringClass().getModifiers()))
      && !method.canAccess(target)) {
      method.setAccessible(true);
    }
  }

  public static boolean isEqualsMethod(Method method) {
    if (method == null || !method.getName().equals("equals")) {
      return false;
    }
    Class<?>[] paramTypes = method.getParameterTypes();
    return (paramTypes.length == 1 && paramTypes[0] == Object.class);
  }

  public static boolean isHashCodeMethod(Method method) {
    return (method != null && method.getName().equals("hashCode") && method.getParameterCount() == 0);
  }

  public static boolean isToStringMethod(Method method) {
    return (method != null && method.getName().equals("toString") && method.getParameterCount() == 0);
  }
}
