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
