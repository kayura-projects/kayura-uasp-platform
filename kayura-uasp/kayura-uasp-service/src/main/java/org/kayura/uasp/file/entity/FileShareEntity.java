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