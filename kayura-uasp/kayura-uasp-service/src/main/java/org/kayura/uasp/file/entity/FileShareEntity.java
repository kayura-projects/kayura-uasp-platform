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

package org.kayura.uasp.file.entity;

import org.kayura.mybatis.annotation.mapper.*;

import java.time.LocalDateTime;

/**
 * 文件分享(uasp_file_share) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_file_share")
public class FileShareEntity {

  /** 文件分享ID */
  @Id
  private String shareId;
  /** 分享者ID */
  private String sharerId;
  /** 接收者Id */
  private String receiverId;
  /** 分享目录ID */
  @ForeignKey(entity = FileFolderEntity.class, alias = "ff")
  private String folderId;
  @RefColumn(from = "ff", value = "name_")
  private String folderName;
  /** 分享时间 */
  private LocalDateTime shareTime;
  /** 失效时间 */
  private LocalDateTime expireTime;
  /** 写权限 */
  private Boolean allowWrite;

  public static FileShareEntity create() {
    return new FileShareEntity();
  }

  public String getShareId() {
    return shareId;
  }

  public FileShareEntity setShareId(String shareId) {
    this.shareId = shareId;
    return this;
  }

  public String getSharerId() {
    return sharerId;
  }

  public FileShareEntity setSharerId(String sharerId) {
    this.sharerId = sharerId;
    return this;
  }

  public String getReceiverId() {
    return receiverId;
  }

  public FileShareEntity setReceiverId(String receiverId) {
    this.receiverId = receiverId;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public FileShareEntity setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public LocalDateTime getShareTime() {
    return shareTime;
  }

  public FileShareEntity setShareTime(LocalDateTime shareTime) {
    this.shareTime = shareTime;
    return this;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public FileShareEntity setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public Boolean getAllowWrite() {
    return allowWrite;
  }

  public FileShareEntity setAllowWrite(Boolean allowWrite) {
    this.allowWrite = allowWrite;
    return this;
  }

  public String getFolderName() {
    return folderName;
  }

  public FileShareEntity setFolderName(String folderName) {
    this.folderName = folderName;
    return this;
  }
}