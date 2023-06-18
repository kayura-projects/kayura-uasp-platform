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

import org.kayura.type.OrderByClause;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * 排序参数解析器。
 *
 * @author liangxia@live.com(夏亮)
 */
public class OrderByClauseArgumentResolver implements HandlerMethodArgumentResolver {

  public static final String REQUEST_ORDER_BY = "orderby";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(OrderByClause.class);
  }

  @Override
  public Object resolveArgument(@NotNull MethodParameter parameter,
                                ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest,
                                WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    String orderByRequest = request.getParameter(REQUEST_ORDER_BY);
    return OrderByClause.resolveForNative(orderByRequest);
  }
}
