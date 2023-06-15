package org.kayura.skeleton.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.kayura.skeleton.mapper")
public class WebStarterConfigurer {
}
