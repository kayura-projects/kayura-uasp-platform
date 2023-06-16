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

package org.kayura.uasp.activiti.entity;

import org.kayura.mybatis.annotation.mapper.*;

@Table("act_hi_procinst")
public class HistoryProcInstEntity {

  @Id
  @Column("ID_")
  private String id;

  @Column("BUSINESS_KEY_")
  private String businessKey;

  @ForeignKey(table = "act_re_procdef", alias = "rpd", pkName = "ID_")
  @Column("PROC_DEF_ID_")
  private String procDefId;

  @RefColumn(from = "rpd", value = "KEY_")
  private String processKey;

  @Column("TENANT_ID_")
  private String tenantId;

  public String getId() {
    return id;
  }

  public HistoryProcInstEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getProcDefId() {
    return procDefId;
  }

  public HistoryProcInstEntity setProcDefId(String procDefId) {
    this.procDefId = procDefId;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public HistoryProcInstEntity setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public HistoryProcInstEntity setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }
}
