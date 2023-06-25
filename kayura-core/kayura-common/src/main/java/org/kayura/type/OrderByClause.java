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

package org.kayura.type;

import org.kayura.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OrderByClause extends ArrayList<OrderByItem> {

  public static final String ASC = "asc";
  public static final String DESC = "desc";
  public static final String ORDERBY_SPLIT = ",";
  public static final String SORT_SPLIT = " ";

  @Override
  public String toString() {
    return "OrderByClause{" + genNativeSql() + "}";
  }

  public String genNativeSql() {
    List<String> orderList = this.stream().map(a -> a.getOrder() + StringUtils.SPACE + a.getDirection())
      .map(String::trim).toList();
    return !orderList.isEmpty() ? StringUtils.join(orderList, ",") : StringUtils.EMPTY;
  }

  public OrderByClause addItem(OrderByItem item) {
    this.add(item);
    return this;
  }

  public static OrderByClause create() {
    return new OrderByClause();
  }

  public static OrderByClause of(String propertyName, String sort) {
    return create().addItem(OrderByItem.of(propertyName, sort));
  }

  public static OrderByClause resolveForNative(String orderByRequest) {

    OrderByClause orderByClause = new OrderByClause();
    if (StringUtils.hasText(orderByRequest)) {
      String[] sp1 = URLDecoder.decode(orderByRequest, StandardCharsets.UTF_8).split(ORDERBY_SPLIT);
      for (String item : sp1) {
        String[] sp2 = item.split(SORT_SPLIT);
        String order = sp2[0].trim();
        String sort = sp2.length > 1 ? sp2[1].trim() : ASC;
        orderByClause.add(new OrderByItem(order, sort));
      }
    }
    return orderByClause;
  }

}
