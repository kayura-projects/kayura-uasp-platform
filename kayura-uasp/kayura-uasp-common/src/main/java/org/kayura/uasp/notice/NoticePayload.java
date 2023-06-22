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
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/** 通知公告 */
public class NoticePayload {

  /** 公告ID */
  @NotBlank(groups = Update.class)
  private String noticeId;
  /** 应用ID */
  @NotBlank(groups = Create.class)
  private String appId;
  /** 公司ID */
  private String tenantId;
  /** 消息类型 */
  @NotBlank
  private String type;
  /** 标题 */
  @NotBlank
  private String title;
  /** 内容 */
  @NotBlank
  private String content;
  /** 展示期限(天) */
  @NotNull
  private Integer expireDay;
  /** 状态:D草搞,V发布,I禁用; */
  private DataStatus status;

  public static NoticePayload create() {
    return new NoticePayload();
  }

  public String getNoticeId() {
    return noticeId;
  }

  public NoticePayload setNoticeId(String noticeId) {
    this.noticeId = noticeId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public NoticePayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public NoticePayload setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getType() {
    return type;
  }

  public NoticePayload setType(String type) {
    this.type = type;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public NoticePayload setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getContent() {
    return content;
  }

  public NoticePayload setContent(String content) {
    this.content = content;
    return this;
  }

  public Integer getExpireDay() {
    return expireDay;
  }

  public NoticePayload setExpireDay(Integer expireDay) {
    this.expireDay = expireDay;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public NoticePayload setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
