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
