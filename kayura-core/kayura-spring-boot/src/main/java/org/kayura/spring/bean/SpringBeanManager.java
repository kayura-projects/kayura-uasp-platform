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

package org.kayura.spring.bean;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class SpringBeanManager implements BeanFactoryAware {

  private static BeanFactory beanFactory = null;

  @Override
  public void setBeanFactory(@NotNull BeanFactory beanFactory) throws BeansException {
    if (SpringBeanManager.beanFactory == null) {
      SpringBeanManager.beanFactory = beanFactory;
    }
  }

  public static BeanFactory getBeanFactory() {
    return beanFactory;
  }

  public static <T> T getBean(Class<T> clazz) {
    if (beanFactory != null) {
      return beanFactory.getBean(clazz);
    }
    return null;
  }

  public static Object getBean(String beanName) {
    if (beanFactory != null) {
      return beanFactory.getBean(beanName);
    }
    return null;
  }

}
