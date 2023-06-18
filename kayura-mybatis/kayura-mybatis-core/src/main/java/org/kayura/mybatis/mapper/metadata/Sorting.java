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

package org.kayura.mybatis.mapper.metadata;

public class Sorting {

  private String owner;
  private String columnName;
  private String columnAlias;
  private int sortIndex;
  private boolean sortDesc;

  public static Sorting builder() {
    return new Sorting();
  }

  public String getOwner() {
    return owner;
  }

  public Sorting setOwner(String owner) {
    this.owner = owner;
    return this;
  }

  public String getColumnName() {
    return columnName;
  }

  public Sorting setColumnName(String columnName) {
    this.columnName = columnName;
    return this;
  }

  public String getColumnAlias() {
    return columnAlias;
  }

  public Sorting setColumnAlias(String columnAlias) {
    this.columnAlias = columnAlias;
    return this;
  }

  public int getSortIndex() {
    return sortIndex;
  }

  public Sorting setSortIndex(int sortIndex) {
    this.sortIndex = sortIndex;
    return this;
  }

  public boolean isSortDesc() {
    return sortDesc;
  }

  public Sorting setSortDesc(boolean sortDesc) {
    this.sortDesc = sortDesc;
    return this;
  }
}
