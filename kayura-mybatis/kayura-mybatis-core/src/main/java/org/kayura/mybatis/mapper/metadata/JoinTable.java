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
