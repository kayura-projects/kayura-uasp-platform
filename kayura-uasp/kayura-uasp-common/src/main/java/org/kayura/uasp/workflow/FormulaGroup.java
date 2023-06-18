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

package org.kayura.uasp.workflow;

import java.util.ArrayList;
import java.util.List;

public class FormulaGroup {

  private String lo;
  private List<FormulaItem> items;

  public String getLo() {
    return lo;
  }

  public FormulaGroup setLo(String lo) {
    this.lo = lo;
    return this;
  }

  public List<FormulaItem> getItems() {
    return items;
  }

  public FormulaGroup setItems(List<FormulaItem> items) {
    this.items = items;
    return this;
  }

  public FormulaGroup addItem(FormulaItem item) {
    if (this.items == null) {
      this.items = new ArrayList<>();
    }
    this.items.add(item);
    return this;
  }
}
