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
