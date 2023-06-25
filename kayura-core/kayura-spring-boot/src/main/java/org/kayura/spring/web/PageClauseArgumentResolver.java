/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
