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

package org.kayura.uasp.user;

import org.kayura.mybatis.annotation.querier.Like;

public class AdminUserQuery {

  private Boolean enabled;
  private Boolean locked;
  @Like("userName,displayName,mobile")
  private String searchText;

  public String getSearchText() {
    return searchText;
  }

  public AdminUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public AdminUserQuery setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public AdminUserQuery setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }
}
