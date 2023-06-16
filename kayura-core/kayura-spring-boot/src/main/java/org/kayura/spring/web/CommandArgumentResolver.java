/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
