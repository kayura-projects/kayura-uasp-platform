/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
