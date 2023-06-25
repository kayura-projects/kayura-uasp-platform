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
