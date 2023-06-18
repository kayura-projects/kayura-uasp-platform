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

package org.kayura.security.jwt;

import org.kayura.security.core.LoginUserImpl;
import org.kayura.security.core.LoginSuccessHandler;
import org.kayura.security.core.LoginUserService;
import org.kayura.utils.StringUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.Cache;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class JwtAuthTokenFilter extends OncePerRequestFilter {

  private final Log LOGGER = LogFactory.getLog(this.getClass());

  private final JwtTokenUtil jwtTokenUtil;
  private final Cache cache;
  private final LoginUserService loginUserService;
  private final LoginSuccessHandler loginSuccessHandler;

  private final String tokenPrefix;
  private final String tokenHeader;

  private AccessDeniedHandler accessDeniedHandler;

  public JwtAuthTokenFilter(JwtTokenUtil jwtTokenUtil,
                            Cache cache,
                            LoginUserService loginUserService,
                            LoginSuccessHandler loginSuccessHandler,
                            String tokenPrefix,
                            String tokenHeader) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.cache = cache;
    this.loginUserService = loginUserService;
    this.loginSuccessHandler = loginSuccessHandler;
    this.tokenPrefix = tokenPrefix;
    this.tokenHeader = tokenHeader;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
                                  @NotNull FilterChain filterChain) throws ServletException, IOException {

    String bearerToken = request.getHeader(tokenHeader);
    if (hasBearerToken(tokenPrefix, bearerToken)) {
      try {
        String jwtToken = bearerToken.substring(tokenPrefix.length());
        Claims claims = jwtTokenUtil.getAllClaimsFromToken(jwtToken);
        if (jwtTokenUtil.isTokenExpired(claims)) {
          accessDeniedHandler.handle(request, response, new JwtExpiredException("使用的令牌已经过期。"));
          return;
        }
        String tokenId = claims.getId();
        LoginUserImpl loginUser = this.cache.get(claims.getId(), LoginUserImpl.class);
        if (loginUser == null) {
          loginUser = renewLoginUser(request, response, claims);
        }
        this.updateActivatedTime(tokenId, loginUser);
        this.saveSecurityContext(loginUser);
      } catch (Exception ex) {
        accessDeniedHandler.handle(request, response, new JwtExpiredException("无法解析传入的令牌。" + ex.getMessage()));
        return;
      }
    }

    filterChain.doFilter(request, response);
  }

  private void updateActivatedTime(String tokenId, LoginUserImpl loginUser) {
    loginUser.setActivatedTime(LocalDateTime.now());
    this.cache.put(tokenId, loginUser);
  }

  private void saveSecurityContext(LoginUserImpl loginUser) {

    assert loginUser != null;
    UsernamePasswordAuthenticationToken authToken =
      new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authToken);
  }

  @NotNull
  private LoginUserImpl renewLoginUser(HttpServletRequest request, @NotNull HttpServletResponse response, Claims claims) {

    LoginUserImpl loginUser = loginUserService.loadUserByUserId(claims.getSubject());
    loginUser.setAppId(claims.getIssuer());
    loginUser.eraseCredentials();
    loginSuccessHandler.handle(request, response, loginUser);
    return loginUser;
  }

  private boolean hasBearerToken(String tokenPrefix, String bearerToken) {
    return StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(tokenPrefix)
      && !bearerToken.equalsIgnoreCase(tokenPrefix + " null");
  }

  public void setAccessDeniedHandler(AccessDeniedHandler accessDeniedHandler) {
    this.accessDeniedHandler = accessDeniedHandler;
  }

}
