package org.kayura.uasp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan("org.kayura.uasp")
@MapperScan("org.kayura.uasp.*.mapper")
public class AutoImportUaspConfigurer {
}
