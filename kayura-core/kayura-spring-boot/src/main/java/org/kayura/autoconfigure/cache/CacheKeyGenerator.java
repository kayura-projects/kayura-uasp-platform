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
