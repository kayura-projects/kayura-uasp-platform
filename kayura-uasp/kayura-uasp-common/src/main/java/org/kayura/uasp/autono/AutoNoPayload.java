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

package org.kayura.uasp.autono;

import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class AutoNoPayload {

  private String defineId;
  @NotBlank(groups = Update.class)
  private String configId;
  private String appId;
  @NotBlank(groups = Create.class)
  private String code;
  @NotBlank(groups = Create.class)
  private String name;
  private String tenantId;
  @NotBlank(groups = Create.class)
  private String expression;
  @Min(value = 0)
  private Integer incValue;
  @Min(value = 2)
  private Integer countLength;
  @NotBlank
  private String customCycle;
  private String remark;

  public static AutoNoPayload create() {
    return new AutoNoPayload();
  }

  public String getConfigId() {
    return configId;
  }

  public AutoNoPayload setConfigId(String configId) {
    this.configId = configId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public AutoNoPayload setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AutoNoPayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public AutoNoPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public AutoNoPayload setName(String name) {
    this.name = name;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AutoNoPayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getExpression() {
    return expression;
  }

  public AutoNoPayload setExpression(String expression) {
    this.expression = expression;
    return this;
  }

  public Integer getIncValue() {
    return incValue;
  }

  public AutoNoPayload setIncValue(Integer incValue) {
    this.incValue = incValue;
    return this;
  }

  public String getCustomCycle() {
    return customCycle;
  }

  public AutoNoPayload setCustomCycle(String customCycle) {
    this.customCycle = customCycle;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AutoNoPayload setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public Integer getCountLength() {
    return countLength;
  }

  public AutoNoPayload setCountLength(Integer countLength) {
    this.countLength = countLength;
    return this;
  }
}
