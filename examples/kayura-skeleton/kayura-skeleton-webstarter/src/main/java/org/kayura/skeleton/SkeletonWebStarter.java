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
public class SkeletonWebStarter implements WebMvcConfigurer {

  /**
   * 主方法，用于启动Skeleton应用程序。
   *
   * @param args 命令行参数。
   */
  public static void main(String[] args) {
    SpringApplication.run(SkeletonWebStarter.class, args);
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
