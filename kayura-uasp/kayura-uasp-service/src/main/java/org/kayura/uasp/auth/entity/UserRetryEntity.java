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