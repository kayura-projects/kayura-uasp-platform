package org.kayura.uasp.workflow;

import java.time.LocalDateTime;

public class UsedOpinionVo {

  private String commentId;
  private String content;
  private Integer useCount;
  private LocalDateTime useTime;

  public static UsedOpinionVo create() {
    return new UsedOpinionVo();
  }

  public String getCommentId() {
    return commentId;
  }

  public UsedOpinionVo setCommentId(String commentId) {
    this.commentId = commentId;
    return this;
  }

  public String getContent() {
    return content;
  }

  public UsedOpinionVo setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public UsedOpinionVo setUseCount(Integer useCount) {
    this.useCount = useCount;
    return this;
  }

  public LocalDateTime getUseTime() {
    return useTime;
  }

  public UsedOpinionVo setUseTime(LocalDateTime useTime) {
    this.useTime = useTime;
    return this;
  }
}
