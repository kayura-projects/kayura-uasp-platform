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

package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.ApproveStatus;
import org.kayura.type.ExtendFields;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.auth.entity.UserEntity;

import java.time.LocalDateTime;

/**
 * uasp_mock_form（模拟表单）实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_mock_form")
public class MockFormEntity {

  @Id
  private String mockId;
  private String usage;
  @ForeignKey(entity = CompanyEntity.class, alias = "tt")
  private String tenantId;
  @RefColumn(from = "tt", value = "full_name_")
  private String tenantName;
  @ForeignKey(entity = FormDefineEntity.class, alias = "fd")
  private String formId;
  @RefColumn(from = "fd", value = "code_")
  private String formCode;
  private String code;
  private String name;
  private ExtendFields extend;
  private String flowCode;
  @ForeignKey(entity = UserEntity.class, alias = "cu")
  private String creatorId;
  @RefColumn(from = "cu", value = "display_name_")
  private String creatorName;
  @Sort(desc = true)
  private LocalDateTime createTime;
  @ForeignKey(entity = UserEntity.class, alias = "uu")
  private String updaterId;
  @RefColumn(from = "uu", value = "display_name_")
  private String updaterName;
  private LocalDateTime updateTime;
  private ApproveStatus status;
  private String remark;

  public static MockFormEntity create() {
    return new MockFormEntity();
  }

  public String getMockId() {
    return mockId;
  }

  public MockFormEntity setMockId(String mockId) {
    this.mockId = mockId;
    return this;
  }

  public String getUsage() {
    return usage;
  }

  public MockFormEntity setUsage(String usage) {
    this.usage = usage;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public MockFormEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public MockFormEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getFormId() {
    return formId;
  }

  public MockFormEntity setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getFormCode() {
    return formCode;
  }

  public MockFormEntity setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public String getCode() {
    return code;
  }

  public MockFormEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public MockFormEntity setName(String name) {
    this.name = name;
    return this;
  }

  public ExtendFields getExtend() {
    return extend;
  }

  public MockFormEntity setExtend(ExtendFields extend) {
    this.extend = extend;
    return this;
  }

  public String getFlowCode() {
    return flowCode;
  }

  public MockFormEntity setFlowCode(String flowCode) {
    this.flowCode = flowCode;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public MockFormEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public String getCreatorName() {
    return creatorName;
  }

  public MockFormEntity setCreatorName(String creatorName) {
    this.creatorName = creatorName;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public MockFormEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public MockFormEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public String getUpdaterName() {
    return updaterName;
  }

  public MockFormEntity setUpdaterName(String updaterName) {
    this.updaterName = updaterName;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public MockFormEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public ApproveStatus getStatus() {
    return status;
  }

  public MockFormEntity setStatus(ApproveStatus status) {
    this.status = status;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public MockFormEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
