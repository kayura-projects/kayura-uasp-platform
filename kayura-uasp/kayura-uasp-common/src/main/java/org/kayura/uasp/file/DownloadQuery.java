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

import org.kayura.mybatis.annotation.querier.In;
import org.kayura.mybatis.annotation.querier.Like;

import java.util.List;

public class DownloadQuery {

  private String linkId;
  @In("linkId")
  private List<String> linkIds;
  private String businessKey;
  @In("businessKey")
  private List<String> businessKeys;
  private String category;
  @Like("tags")
  private String tags;

  public String getLinkId() {
    return linkId;
  }

  public DownloadQuery setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public List<String> getLinkIds() {
    return linkIds;
  }

  public DownloadQuery setLinkIds(List<String> linkIds) {
    this.linkIds = linkIds;
    return this;
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public DownloadQuery setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public List<String> getBusinessKeys() {
    return businessKeys;
  }

  public DownloadQuery setBusinessKeys(List<String> businessKeys) {
    this.businessKeys = businessKeys;
    return this;
  }

  public String getCategory() {
    return category;
  }

  public DownloadQuery setCategory(String category) {
    this.category = category;
    return this;
  }

  public String getTags() {
    return tags;
  }

  public DownloadQuery setTags(String tags) {
    this.tags = tags;
    return this;
  }
}
