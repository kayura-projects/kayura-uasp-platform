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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AutoNoVo {

  private String configId;
  private String defineId;
  private String appId;
  private String appName;
  private String tenantId;
  private String tenantName;
  private String code;
  private String name;
  private String expression;
  private Integer incValue;
  private String customCycle;
  private Integer countLength;
  private String creatorId;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private String remark;

  private Boolean hasCustom = Boolean.FALSE;
  private List<AutoNoCountVo> counts = new ArrayList<>();

  public static AutoNoVo create() {
    return new AutoNoVo();
  }

  public String getCode() {
    return code;
  }

  public AutoNoVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public AutoNoVo setName(String name) {
    this.name = name;
    return this;
  }

  public String getConfigId() {
    return configId;
  }

  public AutoNoVo setConfigId(String configId) {
    this.configId = configId;
    return this;
  }

  public String getExpression() {
    return expression;
  }

  public AutoNoVo setExpression(String expression) {
    this.expression = expression;
    return this;
  }

  public Integer getIncValue() {
    return incValue;
  }

  public AutoNoVo setIncValue(Integer incValue) {
    this.incValue = incValue;
    return this;
  }

  public String getCustomCycle() {
    return customCycle;
  }

  public AutoNoVo setCustomCycle(String customCycle) {
    this.customCycle = customCycle;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public AutoNoVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public AutoNoVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public AutoNoVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public AutoNoVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AutoNoVo setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AutoNoVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public List<AutoNoCountVo> getCounts() {
    return counts;
  }

  public AutoNoVo setCounts(List<AutoNoCountVo> counts) {
    this.counts = counts;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public AutoNoVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AutoNoVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public AutoNoVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public Integer getCountLength() {
    return countLength;
  }

  public AutoNoVo setCountLength(Integer countLength) {
    this.countLength = countLength;
    return this;
  }

  public Boolean getHasCustom() {
    return hasCustom;
  }

  public AutoNoVo setHasCustom(Boolean hasCustom) {
    this.hasCustom = hasCustom;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public AutoNoVo setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }
}
