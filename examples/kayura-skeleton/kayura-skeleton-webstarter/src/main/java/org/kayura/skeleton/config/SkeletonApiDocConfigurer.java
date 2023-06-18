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

package org.kayura.skeleton.config;

import org.kayura.security.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SkeletonApiDocConfigurer类是用于配置WebAPI文档的配置类。
 */
@Configuration
public class SkeletonApiDocConfigurer {

  /**
   * 创建Skeleton WebAPI的Docket对象。
   *
   * @return Skeleton WebAPI的Docket对象。
   */
  @Bean
  public Docket createSkeletonWebApiDocket() {

    return new Docket(DocumentationType.OAS_30)
      .apiInfo(new ApiInfoBuilder().title("Kayura Skeleton WebAPI").build())
      .groupName("Skeleton")
      .ignoredParameterTypes(LoginUser.class)
      .select()
      .apis(RequestHandlerSelectors.basePackage("org.kayura.skeleton.rest"))
      .build();
  }

}
