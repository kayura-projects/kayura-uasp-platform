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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageList<E> implements Serializable {

  private static final long serialVersionUID = 1L;

  private List<E> rows = new ArrayList<>();
  private long totals = 0L;

  public static <E> PageList<E> buildEmpty() {
    return new PageList<>();
  }

  public PageList() {
  }

  public PageList(List<E> rows, long totals) {
    this.rows = rows;
    this.totals = totals;
  }

  public static <E> PageList<E> of(List<E> rows, long totals) {
    return new PageList<>(rows, totals);
  }

  public List<E> getRows() {
    return this.rows;
  }

  public void setRows(List<E> rows) {
    this.rows = rows;
  }

  public long getTotals() {
    return totals;
  }

  public void setTotals(long totals) {
    this.totals = totals;
  }

  public <R> PageList<R> streamMap(Function<? super E, ? extends R> mapper) {
    List<R> collect = this.rows.stream().map(mapper).collect(Collectors.toList());
    return new PageList<>(collect, this.totals);
  }

}
