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
