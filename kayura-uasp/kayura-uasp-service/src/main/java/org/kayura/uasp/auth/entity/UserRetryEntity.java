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