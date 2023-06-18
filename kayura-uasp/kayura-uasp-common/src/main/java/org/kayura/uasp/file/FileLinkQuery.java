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
