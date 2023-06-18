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

import org.kayura.except.ExceptUtils;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 分页参数解析器。
 *
 * @author liangxia@live.com(夏亮)
 */
public class PageClauseArgumentResolver implements HandlerMethodArgumentResolver {

  public static final String PARAM_ORDERBY = "orderby";
  public static final String PARAM_PAGE = "page";
  public static final String PARAM_ROWS = "rows";

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.getParameterType().equals(PageClause.class);
  }

  @Override
  public Object resolveArgument(@NotNull MethodParameter parameter, ModelAndViewContainer mavContainer,
                                NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

    int pageIndex = 1;
    int pageSize = 10;

    String page = request.getParameter(PARAM_PAGE);
    String rows = request.getParameter(PARAM_ROWS);
    String orderby = request.getParameter(PARAM_ORDERBY);

    if (StringUtils.hasText(page)) {
      try {
        pageIndex = Integer.parseInt(page);
      } catch (NumberFormatException ex) {
        ExceptUtils.argument("请求的 page 参数值有误，仅允许为整数。");
      }
    }

    if (StringUtils.hasText(rows)) {
      try {
        pageSize = Integer.parseInt(rows);
      } catch (NumberFormatException ex) {
        ExceptUtils.argument("请求的 rows 参数值有误，仅允许为整数。");
      }
    }

    PageClause pageRequest = new PageClause(pageIndex, pageSize);

    if (StringUtils.hasText(orderby)) {
      orderby = URLDecoder.decode(orderby, StandardCharsets.UTF_8);
      pageRequest.setOrderby(OrderByClause.resolveForNative(orderby));
    }

    return pageRequest;
  }

}
