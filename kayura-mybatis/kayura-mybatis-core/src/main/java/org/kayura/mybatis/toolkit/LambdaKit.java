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

package org.kayura.mybatis.toolkit;

import org.kayura.mybatis.mapper.wrapper.support.Getter;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LambdaKit {

  static Map<Class<?>, WeakReference<SerializedLambda>> CLASS_LAMBDA_CACHE = new ConcurrentHashMap<>();

  public static SerializedLambda resolveLambda(Serializable func) {

    Class<?> funcClass = func.getClass();
    return Optional.ofNullable(CLASS_LAMBDA_CACHE.get(funcClass))
      .map(WeakReference::get)
      .orElseGet(() -> {
        SerializedLambda localLambda;
        try {
          Method method = func.getClass().getDeclaredMethod("writeReplace");
          method.setAccessible(Boolean.TRUE);
          localLambda = (SerializedLambda) method.invoke(func);
          CLASS_LAMBDA_CACHE.put(funcClass, new WeakReference<>(localLambda));
        } catch (Exception e) {
          throw new RuntimeException("发生了什么事情？", e);
        }
        return localLambda;
      });
  }

  public static <T> String columnName(Getter<T> func) {

    SerializedLambda lambda = resolveLambda(func);
    String methodName = lambda.getImplMethodName();

    String prefix = null;
    if (methodName.startsWith("get")) {
      prefix = "get";
    }
    if (methodName.startsWith("is")) {
      prefix = "is";
    }
    assert prefix != null;
    return firstLowerCase(methodName.replaceFirst(prefix, ""));
  }

  public static String firstLowerCase(String param) {
    return param.substring(0, 1).toLowerCase() + param.substring(1);
  }

}
