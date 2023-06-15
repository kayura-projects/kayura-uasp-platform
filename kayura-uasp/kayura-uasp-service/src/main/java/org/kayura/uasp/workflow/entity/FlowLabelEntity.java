package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

import java.time.LocalDateTime;

/**
 * 流转标签(uasp_flow_label) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_flow_label")
public class FlowLabelEntity {

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