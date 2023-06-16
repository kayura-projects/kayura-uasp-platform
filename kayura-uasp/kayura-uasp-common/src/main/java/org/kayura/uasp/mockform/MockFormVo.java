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

package org.kayura.uasp.mockform;

import org.kayura.type.ApproveStatus;
import org.kayura.type.ExtendFields;
import org.kayura.uasp.workflow.AuditTaskVo;

import java.time.LocalDateTime;

public class MockFormVo {

  private String mockId;
  private String usage;
  private String tenantId;
  private String tenantName;
  private String formId;
  private String formCode;
  private String code;
  private String name;
  private ExtendFields extend;
  private String flowCode;
  private String creatorId;
  private String creatorName;
  private LocalDateTime createTime;
  private String updaterId;
  private String updaterName;
  private LocalDateTime updateTime;
  private ApproveStatus status;
  private String remark;
  private AuditTaskVo formTrack;

  public static MockFormVo create() {
    return new MockFormVo();
  }

  public String getFormCode() {
    return formCode;
  }

  public MockFormVo setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public String getMockId() {
    return mockId;
  }

  public MockFormVo setMockId(String mockId) {
    this.mockId = mockId;
    return this;
  }

  public String getUsage() {
    return usage;
  }

  public MockFormVo setUsage(String usage) {
    this.usage = usage;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public MockFormVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public MockFormVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getFormId() {
    return formId;
  }

  public MockFormVo setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public MockFormVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public MockFormVo setName(String name) {
    this.name = name;
    return this;
  }

  public ExtendFields getExtend() {
    return extend;
  }

  public MockFormVo setExtend(ExtendFields extend) {
    this.extend = extend;
    return this;
  }

  public String getFlowCode() {
    return flowCode;
  }

  public MockFormVo setFlowCode(String flowCode) {
    this.flowCode = flowCode;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public MockFormVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public MockFormVo setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public MockFormVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public MockFormVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public MockFormVo setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public MockFormVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public ApproveStatus getStatus() {
    return status;
  }

  public MockFormVo setStatus(ApproveStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public MockFormVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public AuditTaskVo getFormTrack() {
    return formTrack;
  }

  public MockFormVo setFormTrack(AuditTaskVo formTrack) {
    this.formTrack = formTrack;
    return this;
  }
}
