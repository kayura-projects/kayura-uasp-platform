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

package org.kayura.uasp.notice;

import org.kayura.type.DataStatus;
import org.kayura.type.StringList;
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
  /** 附件IDS */
  private StringList attachmentIds;
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

  public StringList getAttachmentIds() {
    return attachmentIds;
  }

  public NoticePayload setAttachmentIds(StringList attachmentIds) {
    this.attachmentIds = attachmentIds;
    return this;
  }
}
