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
