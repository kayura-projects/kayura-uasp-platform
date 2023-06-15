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

import java.util.*;

/**
 * 字符串列表。
 */
public class StringList extends ArrayList<String> {

  public static final StringList EMPTY = new StringList();

  public StringList() {
  }

  public StringList(Collection<String> list) {
    super(list);
  }

  public StringList(String... list) {
    super(Arrays.asList(list));
  }

  public static StringList of(String value) {
    return new StringList(StringUtils.split(value, ","));
  }

  public static StringList of(List<String> list) {
    return new StringList(list);
  }

  public static StringList singletonList(String value) {
    return new StringList(Collections.singletonList(value));
  }
}
