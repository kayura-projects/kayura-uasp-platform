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
