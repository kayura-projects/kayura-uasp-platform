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
