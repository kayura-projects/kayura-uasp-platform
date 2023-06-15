package org.kayura.uasp.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("org.kayura.uasp")
@MapperScan("org.kayura.uasp.*.mapper")
public class AutoImportUaspConfigurer {
}
