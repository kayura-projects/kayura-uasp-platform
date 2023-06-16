package org.kayura.example.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.kayura.example.mapper")
public class WebStarterConfigurer {
}
