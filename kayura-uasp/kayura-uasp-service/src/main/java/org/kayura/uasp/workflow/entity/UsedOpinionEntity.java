/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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