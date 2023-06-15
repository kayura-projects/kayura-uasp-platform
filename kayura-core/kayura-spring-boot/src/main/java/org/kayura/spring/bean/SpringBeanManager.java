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
