package org.kayura.uasp.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidStatViewConfigurer {

  @Bean
  public ServletRegistrationBean<?> statViewServlet() {
    StatViewServlet viewServlet = new StatViewServlet();
    ServletRegistrationBean<StatViewServlet> registrationBean =
      new ServletRegistrationBean<>(viewServlet, "/druid/*");
    return registrationBean;
  }

}
