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

import org.kayura.utils.Assert;

import java.util.Arrays;
import java.util.List;

public final class ClassUtils {

  private static final List<String> PROXY_CLASS_NAMES = Arrays.asList("net.sf.cglib.proxy.Factory"
    , "org.springframework.cglib.proxy.Factory"
    , "javassist.util.proxy.ProxyObject"
    , "org.apache.ibatis.javassist.util.proxy.ProxyObject");

  public static boolean isProxy(Class<?> clazz) {
    if (clazz != null) {
      for (Class<?> cls : clazz.getInterfaces()) {
        if (PROXY_CLASS_NAMES.contains(cls.getName())) {
          return true;
        }
      }
    }
    return false;
  }

  public static Class<?> getUserClass(Class<?> clazz) {
    return isProxy(clazz) ? clazz.getSuperclass() : clazz;
  }

  public static Class<?> getUserClass(Object object) {
    Assert.notNull(object, "Error: Instance must not be null");
    return getUserClass(object.getClass());
  }

}
