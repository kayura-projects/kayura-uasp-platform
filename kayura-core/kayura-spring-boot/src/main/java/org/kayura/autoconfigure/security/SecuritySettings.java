/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
