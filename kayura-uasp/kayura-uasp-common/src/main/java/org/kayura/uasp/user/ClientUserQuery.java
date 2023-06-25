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

package org.kayura.uasp.user;

import org.kayura.mybatis.annotation.Ignore;
import org.kayura.mybatis.annotation.querier.Like;

public class ClientUserQuery {

  private Boolean enabled;
  private Boolean locked;
  @Like("userName,displayName,mobile")
  private String searchText;
  @Ignore
  private Boolean expired;

  public String getSearchText() {
    return searchText;
  }

  public ClientUserQuery setSearchText(String searchText) {
    this.searchText = searchText;
    return this;
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public ClientUserQuery setEnabled(Boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public Boolean getLocked() {
    return locked;
  }

  public ClientUserQuery setLocked(Boolean locked) {
    this.locked = locked;
    return this;
  }

  public Boolean getExpired() {
    return expired;
  }

  public ClientUserQuery setExpired(Boolean expired) {
    this.expired = expired;
    return this;
  }
}
