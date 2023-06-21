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

package org.kayura.uasp.notice;

import org.kayura.mybatis.annotation.querier.Eq;
import org.kayura.mybatis.annotation.querier.Like;

public class NoticeQuery {

  @Eq
  private String appId;

  @Like("title")
  private String searchText;

  public String getAppId() {
    return appId;
  }

  public NoticeQuery setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public NoticeQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
