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
