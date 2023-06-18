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

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PageList<E> implements Serializable {

  @Serial
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
