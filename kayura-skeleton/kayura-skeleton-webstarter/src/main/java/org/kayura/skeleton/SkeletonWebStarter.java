package org.kayura.skeleton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@MapperScan("org.kayura.skeleton.mapper")
public class SkeletonWebStarter implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(SkeletonWebStarter.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/uasp");
  }
}
