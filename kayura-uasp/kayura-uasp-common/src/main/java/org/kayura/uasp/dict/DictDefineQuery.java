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
