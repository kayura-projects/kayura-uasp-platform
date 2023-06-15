package org.kayura.uasp.workflow.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

import java.time.LocalDateTime;

/**
 * 审批常用语(uasp_used_opinion) 实体定义
 *
 * @author liangxia@live.com
 */
@Table("uasp_used_opinion")
public class UsedOpinionEntity {

  @Id
  private String opinionId;
  private String userId;
  private String content;
  private Integer useCount;
  private LocalDateTime useTime;

  public static UsedOpinionEntity create() {
    return new UsedOpinionEntity();
  }

  public String getOpinionId() {
    return opinionId;
  }

  public UsedOpinionEntity setOpinionId(String opinionId) {
    this.opinionId = opinionId;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public UsedOpinionEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public UsedOpinionEntity setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public UsedOpinionEntity setUseCount(Integer useCount) {
    this.useCount = useCount;
    return this;
  }

  public LocalDateTime getUseTime() {
    return useTime;
  }

  public UsedOpinionEntity setUseTime(LocalDateTime useTime) {
    this.useTime = useTime;
    return this;
  }
}