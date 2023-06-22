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

package org.kayura.uasp.notice;

import org.kayura.type.DataStatus;
import org.kayura.uasp.file.FileLinkVo;

import java.time.LocalDateTime;
import java.util.List;

public class NoticeVo {

  private String noticeId;
  private String appId;
  private String appName;
  private String tenantId;
  private String tenantName;
  private String type;
  private String title;
  private String content;
  private LocalDateTime releaseTime;
  private String creatorId;
  private LocalDateTime createTime;
  private String updaterId;
  private LocalDateTime updateTime;
  private Integer expireDay;
  private DataStatus status;

  private List<FileLinkVo> attachments;

  public static NoticeVo create() {
    return new NoticeVo();
  }

  public String getNoticeId() {
    return noticeId;
  }

  public NoticeVo setNoticeId(String noticeId) {
    this.noticeId = noticeId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public NoticeVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getAppName() {
    return appName;
  }

  public NoticeVo setAppName(String appName) {
    this.appName = appName;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public NoticeVo setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public NoticeVo setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getType() {
    return type;
  }

  public NoticeVo setType(String type) {
    this.type = type;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public NoticeVo setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public NoticeVo setContent(String content) {
    this.content = content;
    return this;
  }

  public LocalDateTime getReleaseTime() {
    return releaseTime;
  }

  public NoticeVo setReleaseTime(LocalDateTime releaseTime) {
    this.releaseTime = releaseTime;
    return this;
  }

  public String getCreatorId() {
    return creatorId;
  }

  public NoticeVo setCreatorId(String creatorId) {
    this.creatorId = creatorId;
    return this;
  }

  public LocalDateTime getCreateTime() {
    return createTime;
  }

  public NoticeVo setCreateTime(LocalDateTime createTime) {
    this.createTime = createTime;
    return this;
  }

  public String getUpdaterId() {
    return updaterId;
  }

  public NoticeVo setUpdaterId(String updaterId) {
    this.updaterId = updaterId;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public NoticeVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  public Integer getExpireDay() {
    return expireDay;
  }

  public NoticeVo setExpireDay(Integer expireDay) {
    this.expireDay = expireDay;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public NoticeVo setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public List<FileLinkVo> getAttachments() {
    return attachments;
  }

  public NoticeVo setAttachments(List<FileLinkVo> attachments) {
    this.attachments = attachments;
    return this;
  }
}
