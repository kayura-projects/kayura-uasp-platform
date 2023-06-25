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

package org.kayura.uasp.account;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoginAccountVo {

  private ProfileVo profile;
  private List<MenuVo> mainMenus;
  private Set<String> authorities;
  private Map<String, Set<String>> permissions;

  public static LoginAccountVo create() {
    return new LoginAccountVo();
  }

  public ProfileVo getProfile() {
    return profile;
  }

  public LoginAccountVo setProfile(ProfileVo profile) {
    this.profile = profile;
    return this;
  }

  public List<MenuVo> getMainMenus() {
    return mainMenus;
  }

  public LoginAccountVo setMainMenus(List<MenuVo> mainMenus) {
    this.mainMenus = mainMenus;
    return this;
  }

  public Set<String> getAuthorities() {
    return authorities;
  }

  public LoginAccountVo setAuthorities(Set<String> authorities) {
    this.authorities = authorities;
    return this;
  }

  public Map<String, Set<String>> getPermissions() {
    return permissions;
  }

  public LoginAccountVo setPermissions(Map<String, Set<String>> permissions) {
    this.permissions = permissions;
    return this;
  }
}
