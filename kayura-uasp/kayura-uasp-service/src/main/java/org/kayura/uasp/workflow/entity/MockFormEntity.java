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
