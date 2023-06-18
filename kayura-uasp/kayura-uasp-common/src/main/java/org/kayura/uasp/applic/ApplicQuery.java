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

package org.kayura.uasp.applic;

import org.kayura.mybatis.annotation.querier.Like;
import org.kayura.type.DataStatus;

public class ApplicQuery {

  private Integer level;
  private DataStatus status;
  @Like("code,name")
  private String searchText;

  public static ApplicQuery create() {
    return new ApplicQuery();
  }

  public Integer getLevel() {
    return level;
  }

  public ApplicQuery setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public ApplicQuery setStatus(DataStatus status) {
    this.status = status;
    return this;
  }

  public String getSearchText() {
    return searchText;
  }

  public ApplicQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }
}
