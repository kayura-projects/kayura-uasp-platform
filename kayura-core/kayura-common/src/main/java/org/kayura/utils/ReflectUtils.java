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
