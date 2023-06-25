/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
