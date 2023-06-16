/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
