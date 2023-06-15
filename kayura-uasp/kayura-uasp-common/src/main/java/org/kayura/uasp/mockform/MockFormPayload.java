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
