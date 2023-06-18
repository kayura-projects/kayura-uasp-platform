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

package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.DataStatus;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.workflow.ExprTypes;
import org.kayura.uasp.workflow.FlowLabels;
import org.kayura.uasp.workflow.FormulaGroups;

/**
 * 表单流程(uasp_form_flow) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_form_flow")
public class FormFlowEntity {

  /** 流程ID */
  @Id
  private String flowId;
  /** 业务表单ID */
  @ForeignKey(entity = FormDefineEntity.class, alias = "fd")
  private String formId;
  @RefColumn(from = "fd", value = "code_")
  private String formCode;
  @RefColumn(from = "fd", value = "name_")
  private String formName;
  /** 租户ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "full_name_")
  private String tenantName;
  /** 过程编号 */
  private String processKey;
  /** 流程名 */
  private String displayName;
  /** 流程描述 */
  private String description;
  /** 默认流程 */
  private Boolean primary;
  /** 条件类型:NONE 无条件,INNER 内置,formula 简单表达式,CUSTOM 自定义 */
  private ExprTypes exprType;
  /** 自定义表达式 */
  private FormulaGroups customExpr;
  /** 内部表达式 */
  private String innerExpr;
  /** 流转状态 */
  private FlowLabels flowLabels;
  /** 状态:D草搞,V可用,I禁用; */
  private DataStatus status;

  public static FormFlowEntity create() {
    return new FormFlowEntity();
  }

  public String getFlowId() {
    return flowId;
  }

  public FormFlowEntity setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public String getFormId() {
    return formId;
  }

  public FormFlowEntity setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FormFlowEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getProcessKey() {
    return processKey;
  }

  public FormFlowEntity setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FormFlowEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public FormFlowEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public FormFlowEntity setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  public ExprTypes getExprType() {
    return exprType;
  }

  public FormFlowEntity setExprType(ExprTypes exprType) {
    this.exprType = exprType;
    return this;
  }

  public FormulaGroups getCustomExpr() {
    return customExpr;
  }

  public FormFlowEntity setCustomExpr(FormulaGroups customExpr) {
    this.customExpr = customExpr;
    return this;
  }

  public String getInnerExpr() {
    return innerExpr;
  }

  public FormFlowEntity setInnerExpr(String innerExpr) {
    this.innerExpr = innerExpr;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public FormFlowEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getFormName() {
    return formName;
  }

  public FormFlowEntity setFormName(String formName) {
    this.formName = formName;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public FormFlowEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getFormCode() {
    return formCode;
  }

  public FormFlowEntity setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public FlowLabels getFlowLabels() {
    return flowLabels;
  }

  public FormFlowEntity setFlowLabels(FlowLabels flowLabels) {
    this.flowLabels = flowLabels;
    return this;
  }
}