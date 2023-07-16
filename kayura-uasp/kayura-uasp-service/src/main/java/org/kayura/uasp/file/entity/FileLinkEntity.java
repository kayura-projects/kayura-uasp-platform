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

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.StringList;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.file.FileClassify;
import org.kayura.uasp.file.StoreTypes;

import java.time.LocalDateTime;

/**
 * 文件链接表(uasp_file_link) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_file_link")
public class FileLinkEntity implements Entity {

  /** 文件链接ID */
  @Id
  private String linkId;

  /** 文件ID */
  @ForeignKey(entity = FileStoreEntity.class, alias = "fs")
  private String fileId;
  @RefColumn(from = "fs")
  private Long fileSize;
  @RefColumn(from = "fs")
  private String contentType;
  @RefColumn(from = "fs")
  private FileClassify classify;
  @RefColumn(from = "fs")
  private Boolean readonly;
  @RefColumn(from = "fs")
  private Boolean encrypted;
  @RefColumn(from = "fs")
  private String salt;
  @RefColumn(from = "fs")
  private StoreTypes storeType;
  @RefColumn(from = "fs")
  private String storePath;
  @RefColumn(from = "fs")
  private LocalDateTime lastModified;
  @RefColumn(from = "fs")
  private Boolean exists;

  /** 目录ID */
  private String folderId;
  /** 租户ID */
  @ForeignKey(entity = CompanyEntity.class, alias = "cc")
  private String tenantId;
  @RefColumn(from = "cc", value = "short_name_")
  private String tenantName;
  /** 业务主键 */
  private String businessKey;
  /** 业务类别 */
  private String category;
  /** 显示名 */
  private String displayName;
  /** 扩展名 */
  private String postfix;
  /** 上传者ID */
  private String uploaderId;
  /** 上传者姓名 */
  private String uploaderName;
  /** 上传时间 */
  @Sort(desc = true)
  private LocalDateTime uploadTime;
  /** 下载次数 */
  private Integer downloads;
  /** 排序码 */
  private Integer sort;
  /** 文件标签 */
  private StringList tags;

  public static FileLinkEntity create() {
    return new FileLinkEntity();
  }

  public String getLinkId() {
    return linkId;
  }

  public FileLinkEntity setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public String getFileId() {
    return fileId;
  }

  public FileLinkEntity setFileId(String fileId) {
    this.fileId = fileId;
    return this;
  }

  public Long getFileSize() {
    return fileSize;
  }

  public FileLinkEntity setFileSize(Long fileSize) {
    this.fileSize = fileSize;
    return this;
  }

  public String getContentType() {
    return contentType;
  }

  public FileLinkEntity setContentType(String contentType) {
    this.contentType = contentType;
    return this;
  }

  public Boolean getReadonly() {
    return readonly;
  }

  public FileLinkEntity setReadonly(Boolean readonly) {
    this.readonly = readonly;
    return this;
  }

  public Boolean getEncrypted() {
    return encrypted;
  }

  public FileLinkEntity setEncrypted(Boolean encrypted) {
    this.encrypted = encrypted;
    return this;
  }

  public String getSalt() {
    return salt;
  }

  public FileLinkEntity setSalt(String salt) {
    this.salt = salt;
    return this;
  }

  public StoreTypes getStoreType() {
    return storeType;
  }

  public FileLinkEntity setStoreType(StoreTypes storeType) {
    this.storeType = storeType;
    return this;
  }

  public String getStorePath() {
    return storePath;
  }

  public FileLinkEntity setStorePath(String storePath) {
    this.storePath = storePath;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public FileLinkEntity setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FileLinkEntity setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public FileLinkEntity setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public FileLinkEntity setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public FileLinkEntity setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getPostfix() {
    return postfix;
  }

  public FileLinkEntity setPostfix(String postfix) {
    this.postfix = postfix;
    return this;
  }

  public String getUploaderId() {
    return uploaderId;
  }

  public FileLinkEntity setUploaderId(String uploaderId) {
    this.uploaderId = uploaderId;
    return this;
  }

  public String getUploaderName() {
    return uploaderName;
  }

  public FileLinkEntity setUploaderName(String uploaderName) {
    this.uploaderName = uploaderName;
    return this;
  }

  public LocalDateTime getUploadTime() {
    return uploadTime;
  }

  public FileLinkEntity setUploadTime(LocalDateTime uploadTime) {
    this.uploadTime = uploadTime;
    return this;
  }

  public Integer getDownloads() {
    return downloads;
  }

  public FileLinkEntity setDownloads(Integer downloads) {
    this.downloads = downloads;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FileLinkEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public StringList getTags() {
    return tags;
  }

  public FileLinkEntity setTags(StringList tags) {
    this.tags = tags;
    return this;
  }

  public FileClassify getClassify() {
    return classify;
  }

  public FileLinkEntity setClassify(FileClassify classify) {
    this.classify = classify;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public FileLinkEntity setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public FileLinkEntity setLastModified(LocalDateTime lastModified) {
    this.lastModified = lastModified;
    return this;
  }

  public Boolean getExists() {
    return exists;
  }

  public FileLinkEntity setExists(Boolean exists) {
    this.exists = exists;
    return this;
  }
}