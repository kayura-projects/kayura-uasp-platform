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
