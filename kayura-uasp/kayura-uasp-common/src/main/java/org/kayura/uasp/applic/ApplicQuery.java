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
