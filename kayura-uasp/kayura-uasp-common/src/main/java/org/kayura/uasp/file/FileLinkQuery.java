/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
