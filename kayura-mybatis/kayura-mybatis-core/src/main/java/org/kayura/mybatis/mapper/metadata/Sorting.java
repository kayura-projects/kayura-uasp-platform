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
