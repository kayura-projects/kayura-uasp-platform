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