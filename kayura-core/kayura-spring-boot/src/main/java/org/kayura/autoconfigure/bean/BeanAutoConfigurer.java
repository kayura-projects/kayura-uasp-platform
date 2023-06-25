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
