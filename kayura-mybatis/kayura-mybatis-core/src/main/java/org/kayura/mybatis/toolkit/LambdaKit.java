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
