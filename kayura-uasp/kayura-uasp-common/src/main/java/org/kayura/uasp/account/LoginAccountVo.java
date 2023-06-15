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
