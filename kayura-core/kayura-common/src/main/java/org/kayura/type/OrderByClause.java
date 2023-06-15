/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
