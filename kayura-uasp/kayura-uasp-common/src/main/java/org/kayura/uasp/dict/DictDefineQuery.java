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

package org.kayura.uasp.dict;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

public class DictDefineQuery {

  public static final String APP = "APP";
  public static final String Category = "C";

  @Ignore
  private String type; // APP 应用, C 分类
  @Ignore
  private String appId;
  @Ignore
  private String parentId;
  @Like("code,name")
  private String searchText;

  public String getParentId() {
    return parentId;
  }

  public DictDefineQuery setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getType() {
    return type;
  }

  public DictDefineQuery setType(String type) {
    this.type = type;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public DictDefineQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public DictDefineQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
