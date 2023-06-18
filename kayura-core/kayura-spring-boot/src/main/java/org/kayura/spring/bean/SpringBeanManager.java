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
