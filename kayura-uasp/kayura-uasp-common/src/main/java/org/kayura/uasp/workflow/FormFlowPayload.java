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

package org.kayura.uasp.workflow;

import org.kayura.type.DataStatus;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class FormFlowPayload {

  @NotBlank(groups = Update.class)
  private String flowId;
  @NotBlank(groups = Create.class)
  private String formId;
  @NotBlank(groups = Create.class)
  private String tenantId;
  @NotBlank(groups = Create.class)
  private String processKey;
  @NotBlank(groups = Create.class)
  private String displayName;
  private String description;
  private Boolean primary;
  private ExprTypes exprType;
  private FormulaGroups customExpr;
  private String innerExpr;
  private FlowLabels flowLabels;
  private DataStatus status;

  public String getFormId() {
    return formId;
  }

  public FormFlowPayload setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FormFlowPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public FormFlowPayload setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFlowPayload setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public FormFlowPayload setDescription(String description) {
    this.description = description;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public FormFlowPayload setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  public ExprTypes getExprType() {
    return exprType;
  }

  public FormFlowPayload setExprType(ExprTypes exprType) {
    this.exprType = exprType;
    return this;
  }

  public FormulaGroups getCustomExpr() {
    return customExpr;
  }

  public FormFlowPayload setCustomExpr(FormulaGroups customExpr) {
    this.customExpr = customExpr;
    return this;
  }

  public String getInnerExpr() {
    return innerExpr;
  }

  public FormFlowPayload setInnerExpr(String innerExpr) {
    this.innerExpr = innerExpr;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormFlowPayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getFlowId() {
    return flowId;
  }

  public FormFlowPayload setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public FlowLabels getFlowLabels() {
    return flowLabels;
  }

  public FormFlowPayload setFlowLabels(FlowLabels flowLabels) {
    this.flowLabels = flowLabels;
    return this;
  }
}
