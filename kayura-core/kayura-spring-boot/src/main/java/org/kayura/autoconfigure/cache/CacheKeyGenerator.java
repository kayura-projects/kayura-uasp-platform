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

package org.kayura.autoconfigure.cache;

import org.kayura.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CacheKeyGenerator implements KeyGenerator {

  private final Log logger = LogFactory.getLog(this.getClass());

  @Override
  public @NotNull Object generate(@NotNull Object target, @NotNull Method method, Object @NotNull ... params) {

    CacheKeyGen key = new CacheKeyGen(target, method, params);
    String cacheKey = key.genKey();
    if (logger.isDebugEnabled()) {
      logger.debug("生成缓存Key = " + cacheKey);
    }
    return cacheKey;
  }

  static class CacheKeyGen {

    private final Object[] params;
    private final int hashCode;
    private final String className;
    private final String methodName;

    public CacheKeyGen(Object target, Method method, Object[] elements) {
      this.className = target.getClass().getName();
      this.methodName = getMethodName(method);
      this.params = new Object[elements.length];
      System.arraycopy(elements, 0, this.params, 0, elements.length);
      this.hashCode = generatorHashCode();
    }

    private String getMethodName(Method method) {

      StringBuilder builder = new StringBuilder(method.getName());
      Class<?>[] types = method.getParameterTypes();
      if (types.length != 0) {
        List<String> typeNames = Arrays.stream(types).map(Class::getName).collect(Collectors.toList());
        builder.append("(");
        builder.append(StringUtils.join(typeNames, ","));
        builder.append(")");
      }
      return builder.toString();
    }

    public String genKey() {
      return className + ":" + methodName + ":" + Arrays.deepToString(params);
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      CacheKeyGen o = (CacheKeyGen) obj;
      if (this.hashCode != o.hashCode())
        return false;
      if (!Optional.ofNullable(o.className).orElse("").equals(this.className))
        return false;
      if (!Optional.ofNullable(o.methodName).orElse("").equals(this.methodName))
        return false;
      return Arrays.equals(params, o.params);
    }

    @Override
    public final int hashCode() {
      return hashCode;
    }

    private int generatorHashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + hashCode;
      result = prime * result + ((methodName == null) ? 0 : methodName.hashCode());
      result = prime * result + Arrays.deepHashCode(params);
      result = prime * result + ((className == null) ? 0 : className.hashCode());
      return result;
    }

    @Override
    public String toString() {
      return "CacheKeyGen[className=" + className + ",methodName=" + methodName + ",params=" + Arrays.deepToString(params) + "]";
    }
  }

}
