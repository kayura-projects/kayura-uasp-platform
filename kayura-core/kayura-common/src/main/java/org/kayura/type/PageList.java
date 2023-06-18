/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
