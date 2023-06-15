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
