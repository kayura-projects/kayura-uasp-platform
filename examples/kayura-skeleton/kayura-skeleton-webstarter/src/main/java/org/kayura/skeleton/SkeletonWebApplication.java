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

package org.kayura.skeleton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SkeletonWebStarter类是Skeleton应用的入口类。
 * 使用@SpringBootApplication注解标识为Spring Boot应用程序。
 * 使用@MapperScan注解扫描指定包下的MyBatis映射器接口。
 * 实现WebMvcConfigurer接口以自定义视图控制器。
 */
@SpringBootApplication
@MapperScan("org.kayura.skeleton.mapper")
public class SkeletonWebApplication implements WebMvcConfigurer {

  /**
   * 主方法，用于启动Skeleton应用程序。
   *
   * @param args 命令行参数。
   */
  public static void main(String[] args) {
    SpringApplication.run(SkeletonWebApplication.class, args);
  }

  /**
   * 自定义视图控制器配置，将根路径"/"重定向到"/uasp"。
   *
   * @param registry 视图控制器注册器。
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/uasp");
  }
}
