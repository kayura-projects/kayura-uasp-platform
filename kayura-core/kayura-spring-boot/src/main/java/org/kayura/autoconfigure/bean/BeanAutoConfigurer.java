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
