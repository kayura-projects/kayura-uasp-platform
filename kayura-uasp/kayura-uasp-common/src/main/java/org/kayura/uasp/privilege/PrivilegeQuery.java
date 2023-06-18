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

package org.kayura.uasp.privilege;

import org.kayura.mybatis.annotation.querier.Like;

public class PrivilegeQuery {

  private PrivilegeTypes type;
  private String linkId;
  @Like("code,name")
  private String searchText;

  public static PrivilegeQuery create() {
    return new PrivilegeQuery();
  }

  public String getLinkId() {
    return linkId;
  }

  public PrivilegeQuery setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public PrivilegeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public PrivilegeTypes getType() {
    return type;
  }

  public PrivilegeQuery setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }
}
