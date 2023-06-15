package org.kayura.uasp.file;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

public class FileLinkQuery {

  @Ignore
  private String type; // LATELY, BASIC, FOLDER, RECEIVE, SHARE
  @Ignore
  private String folderId;
  @Ignore
  private FileClassify classify;
  @Ignore
  private String tenantId;
  @Like("displayName")
  private String searchText;

  public String getSearchText() {
    return searchText;
  }

  public FileLinkQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public FileLinkQuery setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public FileLinkQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public FileClassify getClassify() {
    return classify;
  }

  public FileLinkQuery setClassify(FileClassify classify) {
    this.classify = classify;
    return this;
  }

  public String getType() {
    return type;
  }

  public FileLinkQuery setType(String type) {
    this.type = type;
    return this;
  }
}
