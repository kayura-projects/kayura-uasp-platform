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
