/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.example.config;

import org.kayura.security.LoginUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ExampleApiDocConfigurer {

  @Bean
  public Docket createSkeletonWebApiDocket() {

    return new Docket(DocumentationType.OAS_30)
      .apiInfo(new ApiInfoBuilder().title("Kayura Example WebAPI").build())
      .groupName("Example")
      .ignoredParameterTypes(LoginUser.class)
      .select()
      .apis(RequestHandlerSelectors.basePackage("org.kayura.example.rest"))
      .build()
      .globalRequestParameters(genHeaderParameters());
  }

  private List<RequestParameter> genHeaderParameters() {

    RequestParameterBuilder tokenPar = new RequestParameterBuilder();
    tokenPar.name("Authorization")
      .description("Bearer {Token}")
      .in(ParameterType.HEADER)
      .required(false)
      .build();
    List<RequestParameter> pars = new ArrayList<>();
    pars.add(tokenPar.build());
    return pars;
  }

}
