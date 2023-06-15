/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/

package org.kayura.spring.web;

import org.kayura.security.LoginUser;
import org.kayura.security.LoginUserId;
import org.kayura.security.utils.UserContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return LoginUser.class.isAssignableFrom(parameter.getParameterType()) ||
      LoginUserId.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                @NotNull NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
    return UserContext.loginUser();
  }

}
