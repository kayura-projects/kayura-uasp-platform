package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.*;

import java.time.LocalDateTime;

/**
 * 用户尝试登录(uasp_user_retry) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_user_retry")
public class UserRetryEntity {

  /** 用户ID */
  @Id
  private String userId;
  /** 尝试次数 */
  private Integer tryCount;
  /** 允许重试时间 */
  private LocalDateTime allowTime;

  public static UserRetryEntity create() {
    return new UserRetryEntity();
  }

  public String getUserId() {
    return userId;
  }

  public UserRetryEntity setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public Integer getTryCount() {
    return tryCount;
  }

  public UserRetryEntity setTryCount(Integer tryCount) {
    this.tryCount = tryCount;
    return this;
  }

  public LocalDateTime getAllowTime() {
    return allowTime;
  }

  public UserRetryEntity setAllowTime(LocalDateTime allowTime) {
    this.allowTime = allowTime;
    return this;
  }
}