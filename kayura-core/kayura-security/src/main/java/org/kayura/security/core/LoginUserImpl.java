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

package org.kayura.security.core;

import org.kayura.security.LoginUser;
import org.kayura.security.Permission;
import org.kayura.security.PermissionList;
import org.kayura.type.UserTypes;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LoginUserImpl implements LoginUser, UserDetails, CredentialsContainer {

  private String userId;
  private String username;
  private String displayName;
  private String realName;
  private String avatar;
  private String appId;
  private String tenantId;
  private String tenantName;
  private String companyId;
  private String companyName;
  private String departId;
  private String departName;
  private String password;
  private String mobile;
  private UserTypes userType;
  private PermissionList permissions = new PermissionList();
  private HashSet<PrefixGrantedAuthority> authorities = new HashSet<>();
  private boolean accountNonExpired;
  private boolean accountNonLocked;
  private boolean credentialsNonExpired;
  private boolean enabled;
  private LocalDateTime loginTime;
  private LocalDateTime activatedTime;

  public LoginUserImpl() {
  }

  public static LoginUserImpl builder() {
    return new LoginUserImpl();
  }

  public LoginUserImpl setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  @Override
  public String getAppId() {
    return this.appId;
  }

  public String getUserId() {
    return userId;
  }

  public LoginUserImpl setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public LoginUserImpl setUsername(String username) {
    this.username = username;
    return this;
  }

  @Override
  public String getPassword() {
    return password;
  }

  public LoginUserImpl setPassword(String password) {
    this.password = password;
    return this;
  }

  public String getMobile() {
    return mobile;
  }

  public LoginUserImpl setMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public LoginUserImpl setPermissions(PermissionList permissions) {
    this.permissions = permissions;
    return this;
  }

  public PermissionList getPermissions() {
    return permissions;
  }

  @Override
  public HashSet<PrefixGrantedAuthority> getAuthorities() {
    return authorities;
  }

  public LoginUserImpl addAuthority(PrefixGrantedAuthority authority) {
    boolean notExists = true;
    for (PrefixGrantedAuthority x : this.authorities) {
      if (x.getAuthority().equalsIgnoreCase(authority.getAuthority())) {
        notExists = false;
        break;
      }
    }
    if (notExists) {
      this.authorities.add(authority);
    }
    return this;
  }

  public LoginUserImpl setAuthorities(HashSet<PrefixGrantedAuthority> authorities) {
    this.authorities = authorities;
    return this;
  }

  @Override
  public boolean hasPermission(String resource, String... actions) {

    if (StringUtils.hasText(resource)) {
      for (Permission m : this.permissions) {
        if (resource.equalsIgnoreCase(m.getResource())) {
          if (CollectionUtils.isEmpty(actions)) {
            return true;
          }
          Set<String> list = m.getActions();
          if (!list.isEmpty()) {
            for (String x : actions) {
              for (String a : list) {
                if (a.equalsIgnoreCase(x)) {
                  return true;
                }
              }
            }
          }
        }
      }
    }
    return false;
  }

  public boolean hasAnyRole(String... roles) {
    if (roles != null && roles.length > 0) {
      return authorities.stream().anyMatch(x -> Arrays.stream(roles).anyMatch(y -> y.equals(x.getAuthority())));
    }
    return false;
  }

  @Override
  public boolean hasRootOrAdmin() {
    return UserTypes.ROOT.equals(this.userType) || UserTypes.ADMIN.equals(this.userType);
  }

  @Override
  public boolean hasTenantUser() {
    return UserTypes.USER.equals(this.userType) || UserTypes.MANAGER.equals(this.userType);
  }

  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  public LoginUserImpl setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
    return this;
  }

  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  public LoginUserImpl setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
    return this;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  public LoginUserImpl setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
    return this;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public LoginUserImpl setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  @Override
  public void eraseCredentials() {
    this.password = null;
  }

  public String getTenantId() {
    return tenantId;
  }

  public LoginUserImpl setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public LoginUserImpl setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  @Override
  public UserTypes getUserType() {
    return userType;
  }

  public LoginUserImpl setUserType(UserTypes userType) {
    this.userType = userType;
    return this;
  }

  @Override
  public String getDisplayName() {
    return displayName;
  }

  public LoginUserImpl setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getAvatar() {
    return avatar;
  }

  public LoginUserImpl setAvatar(String avatar) {
    this.avatar = avatar;
    return this;
  }

  public LocalDateTime getLoginTime() {
    return loginTime;
  }

  public LoginUserImpl setLoginTime(LocalDateTime loginTime) {
    this.loginTime = loginTime;
    return this;
  }

  public String getDepartId() {
    return departId;
  }

  public LoginUserImpl setDepartId(String departId) {
    this.departId = departId;
    return this;
  }

  public String getTenantName() {
    return tenantName;
  }

  public LoginUserImpl setTenantName(String tenantName) {
    this.tenantName = tenantName;
    return this;
  }

  public String getCompanyName() {
    return companyName;
  }

  public LoginUserImpl setCompanyName(String companyName) {
    this.companyName = companyName;
    return this;
  }

  public String getDepartName() {
    return departName;
  }

  public void setDepartName(String departName) {
    this.departName = departName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public LocalDateTime getActivatedTime() {
    return activatedTime;
  }

  public LoginUserImpl setActivatedTime(LocalDateTime activatedTime) {
    this.activatedTime = activatedTime;
    return this;
  }
}
