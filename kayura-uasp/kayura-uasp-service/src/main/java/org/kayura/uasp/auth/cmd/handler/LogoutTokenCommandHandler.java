/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.autoconfigure.cache.CacheSettings;
import org.kayura.autoconfigure.security.JwtSettings;
import org.kayura.cmd.CommandHandler;
import org.kayura.event.EventGateway;
import org.kayura.security.LoginUser;
import org.kayura.security.jwt.JwtTokenUtil;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.LogoutTokenCommand;
import org.kayura.uasp.auth.event.TokenLogoutEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogoutTokenCommandHandler implements CommandHandler<LogoutTokenCommand, HttpResult> {

  private final Log logger = LogFactory.getLog(this.getClass());
  private final JwtSettings jwtSettings;
  private final JwtTokenUtil jwtTokenUtil;
  private final Cache cache;
  private final EventGateway eventGateway;

  public LogoutTokenCommandHandler(JwtSettings jwtSettings,
                                   JwtTokenUtil jwtTokenUtil,
                                   CacheManager cacheManager,
                                   EventGateway eventGateway) {
    this.jwtSettings = jwtSettings;
    this.jwtTokenUtil = jwtTokenUtil;
    this.cache = cacheManager.getCache(CacheSettings.JWT_TOKEN);
    this.eventGateway = eventGateway;
  }

  @Override
  public HttpResult execute(LogoutTokenCommand command) {

    LoginUser loginUser = command.getLoginUser();
    HttpServletRequest request = command.getRequest();

    String tokenPrefix = jwtSettings.getPrefix();
    String headerToken = request.getHeader(jwtSettings.getHeader());

    try {
      String jwtToken = headerToken.substring(tokenPrefix.length());
      String cacheKey = jwtTokenUtil.getIdFromToken(jwtToken);
      Cache.ValueWrapper wrapper = this.cache.get(cacheKey);
      if (wrapper != null) {
        this.cache.evict(cacheKey);
        return HttpResult.ok("指定 Token 已经注销。");
      }
      eventGateway.publish(new TokenLogoutEvent(this, loginUser, jwtToken));
    } catch (Exception ex) {
      return HttpResult.error("注销Token时发生异常。", ex);
    }

    return HttpResult.error("无法完成注销Token。");
  }

}
