/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
