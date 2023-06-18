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
