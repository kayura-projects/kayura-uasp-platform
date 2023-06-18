/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
