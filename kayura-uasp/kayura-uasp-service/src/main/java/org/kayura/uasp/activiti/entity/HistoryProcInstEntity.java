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
