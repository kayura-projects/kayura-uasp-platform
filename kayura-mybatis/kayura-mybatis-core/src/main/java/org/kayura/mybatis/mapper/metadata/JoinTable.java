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

import org.kayura.mybatis.annotation.mapper.JoinTypes;

public class JoinTable {

  private String table;
  private String alias;
  private String pkName;
  private String from;
  private String fkName;
  private String condition;
  private JoinTypes joinType = JoinTypes.LEFT;

  public String getTable() {
    return table;
  }

  public JoinTable setTable(String table) {
    this.table = table;
    return this;
  }

  public String getAlias() {
    return alias;
  }

  public JoinTable setAlias(String alias) {
    this.alias = alias;
    return this;
  }

  public String getPkName() {
    return pkName;
  }

  public JoinTable setPkName(String pkName) {
    this.pkName = pkName;
    return this;
  }

  public String getFkName() {
    return fkName;
  }

  public JoinTable setFkName(String fkName) {
    this.fkName = fkName;
    return this;
  }

  public JoinTypes getJoinType() {
    return joinType;
  }

  public JoinTable setJoinType(JoinTypes joinType) {
    this.joinType = joinType;
    return this;
  }

  public String getFrom() {
    return from;
  }

  public JoinTable setFrom(String from) {
    this.from = from;
    return this;
  }

  public String getCondition() {
    return condition;
  }

  public JoinTable setCondition(String condition) {
    this.condition = condition;
    return this;
  }
}
