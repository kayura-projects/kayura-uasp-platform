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
