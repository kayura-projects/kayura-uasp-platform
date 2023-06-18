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

package org.kayura.spring.web;

import org.kayura.cmd.CallerSource;
import org.kayura.cmd.Command;
import org.kayura.security.utils.UserContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class CommandArgumentResolver implements HandlerMethodArgumentResolver {

  private final static Log LOG = LogFactory.getLog(CommandArgumentResolver.class);

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return Command.class.isAssignableFrom(parameter.getParameterType());
  }

  @Override
  public Object resolveArgument(@NotNull MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                @NotNull NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) {

    Class<?> callerClass = parameter.getContainingClass();
    Method callerMethod = parameter.getMethod();

    Class<?> type = parameter.getParameterType();
    if (LOG.isDebugEnabled()) {
      StringBuilder sb = new StringBuilder();
      sb.append("Start Resolver -> [").append(callerClass.getName());
      if (callerMethod != null) {
        sb.append(".").append(callerMethod.getName()).append("(");
        Class<?>[] parameterTypes = callerMethod.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
          sb.append(parameterTypes[i].getName());
          if (i < parameterTypes.length - 1) {
            sb.append(", ");
          }
        }
        sb.append(")");
      }
      sb.append("]");
      LOG.debug(sb);
    }

    Object instantiated = BeanUtils.instantiateClass(type);
    Command command = (Command) instantiated;
    command.setSource(CallerSource.create().setClazz(callerClass).setMethod(callerMethod));
    command.setLoginUser(UserContext.loginUser());
    command.setRequest(webRequest.getNativeRequest(HttpServletRequest.class));
    command.setResponse(webRequest.getNativeResponse(HttpServletResponse.class));
    return command;
  }

}
