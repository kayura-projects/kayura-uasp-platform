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

package org.kayura.autoconfigure.security;

import org.kayura.security.encoder.PasswordLevels;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

@ConfigurationProperties(prefix = "kayura.security")
public class SecuritySettings {

  private String[] ignoringUrls = {};
  private String[] permitUrls = {};
  private String[] privilegeUrls = {"/api/**"};
  private String[] whitelist = {"127.0.0.1", "0:0:0:0:0:0:0:1"};
  private Map<String, String> resourceMaps = new HashMap<>();
  private Boolean securedEnabled = Boolean.TRUE;
  private PasswordLevels pwdLevel = PasswordLevels.LOW;

  public String[] getIgnoringUrls() {
    return ignoringUrls;
  }

  public SecuritySettings setIgnoringUrls(String[] ignoringUrls) {
    this.ignoringUrls = ignoringUrls;
    return this;
  }

  public String[] getPermitUrls() {
    return permitUrls;
  }

  public SecuritySettings setPermitUrls(String[] permitUrls) {
    this.permitUrls = permitUrls;
    return this;
  }

  public String[] getPrivilegeUrls() {
    return privilegeUrls;
  }

  public SecuritySettings setPrivilegeUrls(String[] privilegeUrls) {
    this.privilegeUrls = privilegeUrls;
    return this;
  }

  public Map<String, String> getResourceMaps() {
    return resourceMaps;
  }

  public SecuritySettings setResourceMaps(Map<String, String> resourceMaps) {
    this.resourceMaps = resourceMaps;
    return this;
  }

  public PasswordLevels getPwdLevel() {
    return pwdLevel;
  }

  public SecuritySettings setPwdLevel(PasswordLevels pwdLevel) {
    this.pwdLevel = pwdLevel;
    return this;
  }

  public Boolean isSecuredEnabled() {
    return securedEnabled;
  }

  public void setSecuredEnabled(Boolean securedEnabled) {
    this.securedEnabled = securedEnabled;
  }

  public String[] getWhitelist() {
    return whitelist;
  }

  public SecuritySettings setWhitelist(String[] whitelist) {
    this.whitelist = whitelist;
    return this;
  }
}
