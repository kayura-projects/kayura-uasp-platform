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

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.StoreTypes;

import java.time.LocalDateTime;

/**
 * 文件表(uasp_file_info) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_file_store")
public class FileStoreEntity {

  /** 文件ID */
  @Id
  private String fileId;
  /** 大小 */
  private Long fileSize;
  /** 文件类型 */
  private String contentType;
  /** 文件归类：IMAGE,VIDEO,DOC,ZIP,OTHER */
  private FileClassify classify;
  /** 存储方式:nfs,hdfs */
  private StoreTypes storeType;
  /** 存储路径 */
  private String storePath;
  /** 哈希码256 */
  private String hashCode;
  /** 只读文件 */
  private Boolean readonly;
  /** 是否加密 */
  private Boolean encrypted;
  /** 加密盐 */
  private String salt;
  /** 最后修改时间 */
  private LocalDateTime lastModified;
  /** 是否存在 */
  private Boolean exists;

  public static FileStoreEntity create() {
    return new FileStoreEntity();
  }

  public String getFileId() {
    return fileId;
  }

  public FileStoreEntity setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public FileStoreEntity setFileSize(Long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public FileStoreEntity setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public FileStoreEntity setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getHashCode() {
    return hashCode;
  }

  public FileStoreEntity setHashCode(String hashCode) {
    this.hashCode = hashCode;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public FileStoreEntity setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypted() {
    return encrypted;
  }

  public FileStoreEntity setEncrypted(Boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public FileStoreEntity setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public FileStoreEntity setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public FileStoreEntity setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public FileClassify getClassify() {
    return classify;
  }

  public FileStoreEntity setClassify(FileClassify classify) {
    this.classify = classify;
    return this;
  }

  public Boolean getExists() {
    return exists;
  }

  public FileStoreEntity setExists(Boolean exists) {
    this.exists = exists;
    return this;
  }
}