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

package org.kayura.uasp.activiti.model;

public class BizTableInfo {

  private String tableName;
  private String idField;
  private String statusField;
  private String flowField;

  public static BizTableInfo create() {
    return new BizTableInfo();
  }

  public String getTableName() {
    return tableName;
  }

  public BizTableInfo setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getIdField() {
    return idField;
  }

  public BizTableInfo setIdField(String idField) {
    this.idField = idField;
    return this;
  }

  public String getStatusField() {
    return statusField;
  }

  public BizTableInfo setStatusField(String statusField) {
    this.statusField = statusField;
    return this;
  }

  public String getFlowField() {
    return flowField;
  }

  public BizTableInfo setFlowField(String flowField) {
    this.flowField = flowField;
    return this;
  }
}
