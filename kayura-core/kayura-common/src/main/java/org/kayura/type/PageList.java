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
