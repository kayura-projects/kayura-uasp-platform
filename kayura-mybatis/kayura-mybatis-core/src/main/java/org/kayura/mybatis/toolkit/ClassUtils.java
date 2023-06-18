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
