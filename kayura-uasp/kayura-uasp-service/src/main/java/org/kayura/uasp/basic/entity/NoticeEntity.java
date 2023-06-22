/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.ForeignKey;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.RefColumn;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.type.DataStatus;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.organize.entity.CompanyEntity;

import java.time.LocalDateTime;

/**
 * 通知公告(uasp_notice) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_notice")
public class NoticeEntity {

  /** 公告ID */
  @Id
  private String noticeId;
  /** 应用ID */
  @ForeignKey(entity = ApplicEntity.class, alias = "app")
  private String appId;
  @RefColumn(from = "app", value = "name_")
  private String appName;
  /** 公司ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "short_name_")
  private String tenantName;
  /** 消息类型 */
  private String type;
  /** 标题 */
  private String title;
  /** 内容 */
  private String content;
  /** 发布时间 */
  private LocalDateTime releaseTime;
  /** 创建人ID */
  private String creatorId;
  /** 创建时间 */
  private LocalDateTime createTime;
  /** 修改人ID */
  private String updaterId;
  /** 修改时间 */
  private LocalDateTime updateTime;
  /** 展示期限(天) */
  private Integer expireDay;
  /** 状态:D草搞,V发布,I禁用; */
  private DataStatus status;

  public static NoticeEntity create() {
    return new NoticeEntity();
  }

  public String getNoticeId() {
    return noticeId;
  }

  public NoticeEntity setNoticeId(String noticeId) {
    this.noticeId = noticeId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public NoticeEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public NoticeEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getType() {
    return type;
  }

  public NoticeEntity setType(String type) {
    this.type = type;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public NoticeEntity setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public NoticeEntity setContent(String content) {
    this.content = content;
    return this;
  }

  public LocalDateTime getReleaseTime() {
    return releaseTime;
  }

  public NoticeEntity setReleaseTime(LocalDateTime releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public NoticeEntity setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public NoticeEntity setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public NoticeEntity setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public NoticeEntity setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public Integer getExpireDay() {
    return expireDay;
  }

  public NoticeEntity setExpireDay(Integer expireDay) {
    this.expireDay = expireDay;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public NoticeEntity setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public NoticeEntity setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public NoticeEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }
}