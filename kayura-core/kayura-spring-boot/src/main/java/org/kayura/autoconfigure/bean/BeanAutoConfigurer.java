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

package org.kayura.autoconfigure.bean;

import org.kayura.spring.bean.SpringBeanManager;
import org.kayura.type.KeyValueList;
import org.kayura.type.Properties;
import org.kayura.type.StringList;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration
@Import(Jackson2Configurer.class)
public class BeanAutoConfigurer {

  @Bean
  public SpringBeanManager springBeanManager(BeanFactory beanFactory) {
    SpringBeanManager beanManager = new SpringBeanManager();
    beanManager.setBeanFactory(beanFactory);
    return beanManager;
  }

  @Bean
  @ConditionalOnMissingBean(ModelMapper.class)
  public ModelMapper modelMapper(
    ObjectProvider<List<ModelMapperCustomizer>> modelMapperCustomizers
  ) {
    ModelMapper modelMapper = new ModelMapper();
    // StringList
    modelMapper.addConverter(new AbstractConverter<StringList, StringList>() {
      @Override
      protected StringList convert(StringList source) {
        StringList list = new StringList();
        if (!source.isEmpty()) {
          list.addAll(source);
        }
        return list;
      }
    });
    // KeyValueList
    modelMapper.addConverter(new AbstractConverter<KeyValueList, KeyValueList>() {
      @Override
      protected KeyValueList convert(KeyValueList source) {
        KeyValueList list = new KeyValueList();
        if (!source.isEmpty()) {
          list.addAll(source);
        }
        return list;
      }
    });
    // Properties
    modelMapper.addConverter(new AbstractConverter<Properties, Properties>() {
      @Override
      protected Properties convert(Properties source) {
        Properties list = new Properties();
        if (!source.isEmpty()) {
          list.putAll(source);
        }
        return list;
      }
    });
    // 自定义模型映射处理器
    List<ModelMapperCustomizer> mapperCustomizers = modelMapperCustomizers.getIfAvailable();
    if (mapperCustomizers != null && !mapperCustomizers.isEmpty()) {
      mapperCustomizers.forEach(e -> e.customize(modelMapper));
    }
    return modelMapper;
  }

}
