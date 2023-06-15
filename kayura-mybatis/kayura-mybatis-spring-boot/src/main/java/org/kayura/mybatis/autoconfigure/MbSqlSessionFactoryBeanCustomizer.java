package org.kayura.mybatis.autoconfigure;

import org.kayura.mybatis.spring.MbSqlSessionFactoryBean;

@FunctionalInterface
public interface MbSqlSessionFactoryBeanCustomizer {
  void customize(MbSqlSessionFactoryBean factoryBean);
}
