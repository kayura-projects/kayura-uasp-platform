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
