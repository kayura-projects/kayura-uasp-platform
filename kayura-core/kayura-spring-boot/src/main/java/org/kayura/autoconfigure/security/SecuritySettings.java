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
