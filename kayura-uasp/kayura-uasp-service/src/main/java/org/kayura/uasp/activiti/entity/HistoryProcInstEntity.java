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
