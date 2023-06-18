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
