/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.toolkit;

import org.kayura.mybatis.exceptions.ExceptUtils;
import org.kayura.utils.Assert;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.ReflectUtils;
import org.kayura.utils.StringUtils;

import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

public abstract class ReflectKit {

  private static final String GET = "get";
  private static final String SET = "set";
  private static final String IS = "is";

  private static final Map<Class<?>, List<Field>> CLASS_FIELD_CACHE = new ConcurrentHashMap<>();
  private static final Map<Class<?>, List<Method>> CLASS_METHOD_CACHE = new ConcurrentHashMap<>();
  private static final Map<Class<?>, Class<?>> PRIMITIVE_WRAPPER_TYPE_MAP = new IdentityHashMap<>(8);

  static {
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Boolean.class, boolean.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Byte.class, byte.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Character.class, char.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Double.class, double.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Float.class, float.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Integer.class, int.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Long.class, long.class);
    PRIMITIVE_WRAPPER_TYPE_MAP.put(Short.class, short.class);
  }

  public static List<Field> getEntityFields(Class<?> clazz) {
    if (clazz.getSuperclass() != null) {
      List<Field> fieldList = Stream.of(clazz.getDeclaredFields())
        .filter(field -> !Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers()))
        .collect(toCollection(LinkedList::new));
      Class<?> superClass = clazz.getSuperclass();
      return excludeOverrideSuperField(fieldList, getEntityFields(superClass));
    } else {
      return Collections.emptyList();
    }
  }

  public static List<Field> excludeOverrideSuperField(List<Field> fieldList, List<Field> superFieldList) {
    Map<String, Field> fieldMap = fieldList.stream().collect(toMap(Field::getName, identity()));
    superFieldList.stream().filter(field -> !fieldMap.containsKey(field.getName())).forEach(fieldList::add);
    return fieldList;
  }

  public static List<Method> getEntityMethods(Class<?> clazz) {
    if (clazz.getSuperclass() != null) {
      List<Method> fieldList = Stream.of(clazz.getDeclaredMethods())
        .filter(method -> isFieldMethod(method) && !Modifier.isStatic(method.getModifiers()) && !Modifier.isTransient(method.getModifiers()))
        .collect(toCollection(LinkedList::new));
      Class<?> superClass = clazz.getSuperclass();
      return excludeOverrideSuperMethod(fieldList, getEntityMethods(superClass));
    } else {
      return Collections.emptyList();
    }
  }

  private static boolean isFieldMethod(Method method) {
    String methodName = method.getName();
    return methodName.startsWith(GET) || methodName.startsWith(IS) || methodName.startsWith(SET);
  }

  public static List<Method> excludeOverrideSuperMethod(List<Method> fieldList, List<Method> superFieldList) {
    Map<String, Method> fieldMap = fieldList.stream().collect(toMap(Method::getName, identity()));
    superFieldList.stream().filter(field -> !fieldMap.containsKey(field.getName())).forEach(fieldList::add);
    return fieldList;
  }

  public static boolean isPrimitiveOrWrapper(Class<?> clazz) {
    Assert.notNull(clazz, "Class must not be null");
    return (clazz.isPrimitive() || PRIMITIVE_WRAPPER_TYPE_MAP.containsKey(clazz));
  }

  public static Class<?> getSuperClassGenericType(Class<?> clazz) {
    return getSuperClassGenericType(clazz, 0);
  }

  public static Class<?> getSuperClassGenericType(Class<?> clazz, int index)
    throws IndexOutOfBoundsException {
    Class<?> entityClass = null;
    Type genericSuperclass = clazz.getGenericSuperclass();
    if (genericSuperclass instanceof ParameterizedType) {
      Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
      if (index < actualTypeArguments.length) {
        entityClass = (Class<?>) actualTypeArguments[index];
      }
    }
    return entityClass;
  }

  public static Object getFieldValue(final String fieldName, Object entity) {
    try {
      Class<?> entityClass = entity.getClass();
      List<Field> fields = getEntityFields(entityClass);
      Optional<Field> first = fields.stream().filter(a -> fieldName.equals(a.getName())).findFirst();
      if (first.isPresent()) {
        first.get().setAccessible(true);
        return first.get().get(entity);
      }
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static Map<String, Field> getFieldMap(Class<?> clazz) {
    List<Field> fieldList = getCacheFields(clazz);
    return CollectionUtils.isNotEmpty(fieldList)
      ? fieldList.stream().collect(Collectors.toMap(Field::getName, field -> field))
      : Collections.emptyMap();
  }

  public static List<Field> getCacheFields(Class<?> clazz) {
    if (Objects.isNull(clazz)) {
      return Collections.emptyList();
    }
    List<Field> fields = CLASS_FIELD_CACHE.get(clazz);
    if (CollectionUtils.isEmpty(fields)) {
      synchronized (CLASS_FIELD_CACHE) {
        fields = doGetFieldList(clazz);
        CLASS_FIELD_CACHE.put(clazz, fields);
      }
    }
    return fields;
  }

  public static void setFieldValue(final String fieldName, Object entity, Object value) {
    try {
      Map<String, Field> fieldMap = getFieldMap(entity.getClass());
      Field declaredField = fieldMap.getOrDefault(fieldName, null);
      Assert.notNull(declaredField, "NoSuchField in %s for %s.", fieldName, entity.getClass().getSimpleName());
      ReflectUtils.makeAccessible(declaredField, entity);
      declaredField.set(entity, value);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  public static Object getMethodValue(Object entity, String property) {
    return getMethodValue(entity.getClass(), entity, property);
  }

  public static Object getMethodValue(Class<?> entityClass, Object entity, String property) {
    Map<String, Field> fieldMaps = getFieldMap(entityClass);
    try {
      Assert.notEmpty(fieldMaps, "Error: NoSuchField in %s for %s.  Cause:", entityClass.getSimpleName(), property);
      String methodName = readGetMethodCapitalize(fieldMaps.getOrDefault(property, null), property);
      Method method = entityClass.getMethod(methodName);
      return method.invoke(entity);
    } catch (NoSuchMethodException e) {
      throw ExceptUtils.except("Error: NoSuchMethod in %s.  Cause:", e, entityClass.getSimpleName());
    } catch (IllegalAccessException e) {
      throw ExceptUtils.except("Error: Cannot execute a private method. in %s.  Cause:", e, entityClass.getSimpleName());
    } catch (InvocationTargetException e) {
      throw ExceptUtils.except("Error: InvocationTargetException on getMethodValue.  Cause:" + e);
    }
  }

  public static String readGetMethodCapitalize(Field field, final String property) {
    if (field != null) {
      Class<?> fieldType = field.getType();
      // fix #176
      return StringUtils.concatCapitalize(boolean.class.equals(fieldType) ? IS : GET, property);
    } else {
      return StringUtils.concatCapitalize(GET, property);
    }
  }

  public static String readSetMethodCapitalize(final String property) {
    return StringUtils.concatCapitalize(SET, property);
  }

  private static List<Field> doGetFieldList(Class<?> clazz) {
    if (clazz.getSuperclass() != null) {
      List<Field> fieldList = Stream.of(clazz.getDeclaredFields())
        .filter(field -> !Modifier.isStatic(field.getModifiers()))
        .filter(field -> !Modifier.isTransient(field.getModifiers()))
        .collect(toCollection(LinkedList::new));
      Class<?> superClass = clazz.getSuperclass();
      return excludeOverrideSuperField(fieldList, getCacheFields(superClass));
    } else {
      return Collections.emptyList();
    }
  }

  public static void setMethodValue(Object entity, String property, Object value) {
    setMethodValue(entity.getClass(), entity, property, value);
  }

  public static void setMethodValue(Class<?> entityClass, Object entity, String property, Object value) {
    try {
      String methodName = readSetMethodCapitalize(property);
      List<Method> methods = getEntityMethods(entityClass);
      Method writeMethod = methods.stream().filter(m -> methodName.equals(m.getName())).findFirst().orElse(null);
      assert writeMethod != null;
      writeMethod.setAccessible(true);
      writeMethod.invoke(entity, value);
    } catch (IllegalAccessException | InvocationTargetException e) {
      throw ExceptUtils.except("Error: Cannot execute a private method. in %s.  Cause:", e, entityClass.getSimpleName());
    }
  }
}
