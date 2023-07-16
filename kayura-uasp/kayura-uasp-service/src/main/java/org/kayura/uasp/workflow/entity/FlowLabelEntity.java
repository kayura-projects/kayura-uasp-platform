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

package org.kayura.uasp.workflow.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

import java.time.LocalDateTime;

/**
 * 流转标签(uasp_flow_label) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_flow_label")
public class FlowLabelEntity implements Entity {

  @Id
  private String businessKey;
  private String labelValue;
  private LocalDateTime updateTime;

  public static FlowLabelEntity create() {
    return new FlowLabelEntity();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public FlowLabelEntity setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getLabelValue() {
    return labelValue;
  }

  public FlowLabelEntity setLabelValue(String labelValue) {
    this.labelValue = labelValue;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public FlowLabelEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}