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

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.entity.CompanyEntity;

import java.time.LocalDateTime;

/**
 * 自动编号配置(uasp_auto_no_config) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_auto_no_config")
public class AutoNoConfigEntity {

  /** 编号详情ID */
  @Id
  private String configId;
  /** 自动编号ID */
  @ForeignKey(entity = AutoNoDefineEntity.class, alias = "ad")
  private String defineId;
  @RefColumn(from = "ad")
  @ForeignKey(entity = ApplicEntity.class, alias = "ap")
  private String appId;
  @RefColumn(from = "ap", value = "name_")
  private String appName;
  @RefColumn(from = "ad")
  private String code;
  @RefColumn(from = "ad")
  private String name;
  /** 租户ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "short_name_")
  private String tenantName;
  /** 表达式:${类型}-${年}-${月}-${日}-${计数} */
  private String expression;
  /** 增量值 */
  private Integer incValue;
  /** 计数长度 */
  private Integer countLength;
  /** 定义周期 */
  private String customCycle;
  /** 创建人ID */
  private String creatorId;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  private String updaterId;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 备注 */
  private String remark;

  public static AutoNoConfigEntity create() {
    return new AutoNoConfigEntity();
  }

  public String getConfigId() {
    return configId;
  }

  public AutoNoConfigEntity setConfigId(String configId) {
    this.configId = configId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public AutoNoConfigEntity setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AutoNoConfigEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getExpression() {
    return expression;
  }

  public AutoNoConfigEntity setExpression(String expression) {
    this.expression = expression;
    return this;
  }

  public Integer getIncValue() {
    return incValue;
  }

  public AutoNoConfigEntity setIncValue(Integer incValue) {
    this.incValue = incValue;
    return this;
  }

  public String getCustomCycle() {
    return customCycle;
  }

  public AutoNoConfigEntity setCustomCycle(String customCycle) {
    this.customCycle = customCycle;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public AutoNoConfigEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public AutoNoConfigEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public AutoNoConfigEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public AutoNoConfigEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public AutoNoConfigEntity setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public String getCode() {
    return code;
  }

  public AutoNoConfigEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public AutoNoConfigEntity setName(String name) {
    this.name = name;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AutoNoConfigEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Integer getCountLength() {
    return countLength;
  }

  public AutoNoConfigEntity setCountLength(Integer countLength) {
    this.countLength = countLength;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public AutoNoConfigEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public AutoNoConfigEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }
}