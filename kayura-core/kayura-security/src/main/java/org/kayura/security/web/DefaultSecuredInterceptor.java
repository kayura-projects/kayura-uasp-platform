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

package org.kayura.security.web;

import org.kayura.security.LoginUser;
import org.kayura.security.annotation.Secured;
import org.kayura.security.core.SecuredItem;
import org.kayura.security.utils.UserContext;
import org.kayura.type.UserTypes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DefaultSecuredInterceptor implements SecuredInterceptor {

  private static final Log LOGGER = LogFactory.getLog(DefaultSecuredInterceptor.class);
  private static final Map<Class<?>, List<SecuredItem>> SECURED_CACHE = new ConcurrentHashMap<>();

  private AccessDeniedHandler accessDeniedHandler;
  private boolean securedEnabled = true;


  public boolean preHandle(@NotNull HttpServletRequest request,
                           @NotNull HttpServletResponse response,
                           @NotNull Object handler) throws Exception {

    if (this.securedEnabled && handler instanceof HandlerMethod) {

      List<SecuredItem> securedItems;

      Object bean = ((HandlerMethod) handler).getBean();

      if (SECURED_CACHE.containsKey(bean.getClass())) {
        securedItems = SECURED_CACHE.get(bean.getClass());
      } else {

        // 获取方法上的注解
        List<Secured> securedList = new ArrayList<>();
        Method method = ((HandlerMethod) handler).getMethod();
        Secured[] methodSecures = method.getAnnotationsByType(Secured.class);
        if (methodSecures.length > 0) {
          securedList.addAll(Arrays.asList(methodSecures));
        }
        // 获取对象上的注解
        Secured[] beanSecures = bean.getClass().getAnnotationsByType(Secured.class);
        if (beanSecures.length > 0) {
          securedList.addAll(Arrays.asList(beanSecures));
        }

        // 提取数据
        securedItems = new ArrayList<>();
        for (Secured beanSecured : securedList) {
          if (beanSecured.resource().length > 0) {
            for (String resource : beanSecured.resource()) {
              securedItems.add(
                SecuredItem.builder()
                  .setResource(resource)
                  .setActions(beanSecured.actions())
                  .setRoles(beanSecured.roles())
              );
            }
          } else if (beanSecured.roles().length > 0) {
            securedItems.add(SecuredItem.builder().setRoles(beanSecured.roles()));
          }
        }

        SECURED_CACHE.put(bean.getClass(), securedItems);
      }

      if (!securedItems.isEmpty()) {

        LoginUser loginUser = UserContext.loginUser();
        // 如果是登录用户且为根管理员，将自动获得权限
        boolean hasPrivilege = loginUser != null && UserTypes.ROOT.equals(loginUser.getUserType());
        if (loginUser != null && !hasPrivilege) {
          for (SecuredItem securedItem : securedItems) {
            if (loginUser.hasPermission(securedItem.getResource(), securedItem.getActions()) ||
              loginUser.hasAnyRole(securedItem.getRoles())) {
              return true; // 获得授权立即结束
            }
          }
        }

        if (!hasPrivilege) {
          PermissionDeniedException exception = PermissionDeniedException.create("缺少访问授权。", securedItems);
          if (LOGGER.isWarnEnabled()) {
            LOGGER.warn(exception.getMessage());
          }
          accessDeniedHandler.handle(request, response, exception);
          return false;
        }
      }
    }

    return true;
  }

  public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
    this.accessDeniedHandler = accessDeniedHandler;
  }

  public void setSecuredEnabled(boolean securedEnabled) {
    this.securedEnabled = securedEnabled;
  }
}
