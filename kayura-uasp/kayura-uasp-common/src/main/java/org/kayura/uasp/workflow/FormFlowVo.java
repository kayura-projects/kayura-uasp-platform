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

package org.kayura.uasp.workflow;

import org.kayura.type.DataStatus;

import java.time.LocalDateTime;

public class FormFlowVo {

  private String flowId;
  private String formId;
  private String formName;
  private String tenantId;
  private String tenantName;
  private String processKey;
  private String displayName;
  private String description;
  private Boolean primary;
  private ExprTypes exprType;
  private FormulaGroups customExpr;
  private String innerExpr;
  private FlowLabels flowLabels;
  private DataStatus status;

  private String modelId;
  private String modelContent;
  private LocalDateTime lastUpdateTime;
  private String lastDeployId;
  private Integer lastVersion;
  private LocalDateTime lastDeployTime;

  public String getFlowId() {
    return flowId;
  }

  public FormFlowVo setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public String getFormId() {
    return formId;
  }

  public FormFlowVo setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getFormName() {
    return formName;
  }

  public FormFlowVo setFormName(String formName) {
    this.formName = formName;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FormFlowVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public FormFlowVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public FormFlowVo setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFlowVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public FormFlowVo setDescription(String description) {
    this.description = description;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public FormFlowVo setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  public ExprTypes getExprType() {
    return exprType;
  }

  public FormFlowVo setExprType(ExprTypes exprType) {
    this.exprType = exprType;
    return this;
  }

  public String getExprTypeName() {
    return exprType != null ? exprType.getName() : null;
  }

  public FormulaGroups getCustomExpr() {
    return customExpr;
  }

  public FormFlowVo setCustomExpr(FormulaGroups customExpr) {
    this.customExpr = customExpr;
    return this;
  }

  public String getInnerExpr() {
    return innerExpr;
  }

  public FormFlowVo setInnerExpr(String innerExpr) {
    this.innerExpr = innerExpr;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormFlowVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getModelId() {
    return modelId;
  }

  public FormFlowVo setModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }

  public LocalDateTime getLastUpdateTime() {
    return lastUpdateTime;
  }

  public FormFlowVo setLastUpdateTime(LocalDateTime lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
    return this;
  }

  public LocalDateTime getLastDeployTime() {
    return lastDeployTime;
  }

  public FormFlowVo setLastDeployTime(LocalDateTime lastDeployTime) {
    this.lastDeployTime = lastDeployTime;
    return this;
  }

  public String getModelContent() {
    return modelContent;
  }

  public FormFlowVo setModelContent(String modelContent) {
    this.modelContent = modelContent;
    return this;
  }

  public String getLastDeployId() {
    return lastDeployId;
  }

  public FormFlowVo setLastDeployId(String lastDeployId) {
    this.lastDeployId = lastDeployId;
    return this;
  }

  public FlowLabels getFlowLabels() {
    return flowLabels;
  }

  public FormFlowVo setFlowLabels(FlowLabels flowLabels) {
    this.flowLabels = flowLabels;
    return this;
  }

  public Integer getLastVersion() {
    return lastVersion;
  }

  public FormFlowVo setLastVersion(Integer lastVersion) {
    this.lastVersion = lastVersion;
    return this;
  }
}
