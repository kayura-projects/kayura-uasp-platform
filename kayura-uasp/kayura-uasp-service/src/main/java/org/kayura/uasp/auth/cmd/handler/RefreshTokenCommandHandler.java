package org.kayura.uasp.auth.cmd.handler;

import org.kayura.autoconfigure.cache.CacheSettings;
import org.kayura.autoconfigure.security.JwtSettings;
import org.kayura.cmd.CommandHandler;
import org.kayura.event.EventGateway;
import org.kayura.security.LoginUser;
import org.kayura.security.jwt.JwtToken;
import org.kayura.security.jwt.JwtTokenUtil;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.RefreshTokenCommand;
import org.kayura.uasp.auth.event.TokenCreatedEvent;
import org.kayura.uasp.auth.event.TokenLogoutEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class RefreshTokenCommandHandler implements CommandHandler<RefreshTokenCommand, HttpResult> {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final JwtSettings jwtSettings;
  private final JwtTokenUtil jwtTokenUtil;
  private final Cache cache;
  private final EventGateway eventGateway;

  public RefreshTokenCommandHandler(JwtSettings jwtSettings,
                                    JwtTokenUtil jwtTokenUtil,
                                    CacheManager cacheManager,
                                    EventGateway eventGateway) {
    this.jwtSettings = jwtSettings;
    this.jwtTokenUtil = jwtTokenUtil;
    this.cache = cacheManager.getCache(CacheSettings.JWT_TOKEN);
    this.eventGateway = eventGateway;
  }

  @Override
  public HttpResult execute(RefreshTokenCommand command) {

    HttpServletRequest request = command.getRequest();
    LoginUser loginUser = command.getLoginUser();

    String tokenPrefix = jwtSettings.getPrefix();
    String headerToken = request.getHeader(jwtSettings.getHeader());

    try {
      String oldToken = headerToken.substring(tokenPrefix.length());
      eventGateway.publish(new TokenLogoutEvent(this, loginUser, oldToken));
      JwtToken refreshToken = jwtTokenUtil.refreshToken(oldToken);
      String cacheKey = jwtTokenUtil.getIdFromToken(oldToken);
      this.cache.evict(cacheKey);
      String newToken = refreshToken.getToken();
      eventGateway.publish(new TokenCreatedEvent(this, loginUser, newToken));
      return HttpResult.okBody(newToken);
    } catch (Exception ex) {
      logger.error("刷新 Token 时发生异常。", ex);
    }

    return HttpResult.error("无法提供 Token 刷新调用。");
  }

}
