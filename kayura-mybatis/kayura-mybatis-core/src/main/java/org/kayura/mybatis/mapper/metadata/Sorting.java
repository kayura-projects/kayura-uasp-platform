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
