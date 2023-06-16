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

import org.kayura.type.ExtendFields;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;

public class MockFormPayload {

  @NotBlank(groups = Update.class)
  private String mockId;
  @NotBlank(groups = Create.class)
  private String usage;
  @NotBlank(groups = Create.class)
  private String tenantId;
  @NotBlank(groups = Create.class)
  private String formId;
  @NotBlank
  private String code;
  @NotBlank
  private String name;
  private ExtendFields extend;
  private String remark;

  public static MockFormPayload create() {
    return new MockFormPayload();
  }

  public String getMockId() {
    return mockId;
  }

  public MockFormPayload setMockId(String mockId) {
    this.mockId = mockId;
    return this;
  }

  public String getUsage() {
    return usage;
  }

  public MockFormPayload setUsage(String usage) {
    this.usage = usage;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public MockFormPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getFormId() {
    return formId;
  }

  public MockFormPayload setFormId(String formId) {
    this.formId = formId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public MockFormPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public MockFormPayload setName(String name) {
    this.name = name;
    return this;
  }

  public ExtendFields getExtend() {
    return extend;
  }

  public MockFormPayload setExtend(ExtendFields extend) {
    this.extend = extend;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public MockFormPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }
}
