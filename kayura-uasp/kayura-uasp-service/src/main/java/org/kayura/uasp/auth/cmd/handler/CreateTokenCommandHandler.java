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

package org.kayura.uasp.auth.cmd.handler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kayura.cmd.CommandHandler;
import org.kayura.event.EventGateway;
import org.kayura.security.core.LoginUserImpl;
import org.kayura.security.jwt.JwtTokenUtil;
import org.kayura.security.retry.RetryLimitStrategy;
import org.kayura.security.retry.RetryResult;
import org.kayura.security.utils.IpWhitelist;
import org.kayura.security.vcode.ImageVerifyHandler;
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.auth.cmd.CreateTokenCommand;
import org.kayura.uasp.auth.event.LoginFailureEvent;
import org.kayura.uasp.auth.event.LoginSuccessEvent;
import org.kayura.uasp.auth.event.TokenCreatedEvent;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.auth.service.PassportService;
import org.kayura.uasp.config.AppSettings;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.passport.LoginPayload;
import org.kayura.uasp.passport.LoginTypes;
import org.kayura.utils.HttpUtils;
import org.kayura.utils.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Component
public class CreateTokenCommandHandler implements CommandHandler<CreateTokenCommand, HttpResult> {

  private final Log logger = LogFactory.getLog(CreateTokenCommandHandler.class);

  private final JwtTokenUtil jwtTokenUtil;
  private final PasswordEncoder passwordEncoder;
  private final UserManager userManager;
  private final ImageVerifyHandler imageVerifyHandler;
  private final SmsVerifyHandler smsVerifyHandler;
  private final RetryLimitStrategy retryLimitStrategy;
  private final PassportService passportService;
  private final ApplicManager applicManager;
  private final AppSettings appSettings;
  private final IpWhitelist ipWhitelist;
  private final EventGateway eventGateway;

  public CreateTokenCommandHandler(JwtTokenUtil jwtTokenUtil,
                                   PasswordEncoder passwordEncoder,
                                   UserManager userManager,
                                   ImageVerifyHandler imageVerifyHandler,
                                   SmsVerifyHandler smsVerifyHandler,
                                   RetryLimitStrategy retryLimitStrategy,
                                   PassportService passportService,
                                   ApplicManager applicManager,
                                   AppSettings appSettings,
                                   IpWhitelist ipWhitelist,
                                   EventGateway eventGateway) {
    this.jwtTokenUtil = jwtTokenUtil;
    this.passwordEncoder = passwordEncoder;
    this.userManager = userManager;
    this.imageVerifyHandler = imageVerifyHandler;
    this.smsVerifyHandler = smsVerifyHandler;
    this.retryLimitStrategy = retryLimitStrategy;
    this.passportService = passportService;
    this.applicManager = applicManager;
    this.appSettings = appSettings;
    this.ipWhitelist = ipWhitelist;
    this.eventGateway = eventGateway;
  }

  @Transactional
  public HttpResult execute(CreateTokenCommand command) {

    HttpServletRequest request = command.getRequest();
    LoginPayload payload = command.getPayload();

    String sessionId = request.getSession(true).getId();

    // 验证码检查
    if (LoginTypes.SMS.equals(payload.getType())) {
      Result verify = smsVerifyHandler.verify(payload.getUsername(), payload.getUcode(), payload.getVcode());
      if (verify.isFail()) {
        return HttpResult.of(verify);
      }
    } else {
      if (imageVerifyHandler.isNeed(sessionId)) {
        Result verify = imageVerifyHandler.verify(sessionId, payload.getVcode());
        if (verify.isFail()) {
          return HttpResult.of(verify);
        }
      }
      // 检查登录限制
      Result verify = retryLimitStrategy.retryCheck(payload.getUsername());
      if (verify.isFail()) {
        return HttpResult.of(verify);
      }
    }

    // 账号检查
    LoginUserImpl loginUser;
    try {
      // 加载用户信息
      loginUser = passportService.loadUserByUsername(payload.getUsername());

      // 白名单检查
      if (loginUser.hasRootOrAdmin()) {
        String clientIp = HttpUtils.readClientIp(request);
        if (!ipWhitelist.isAllowed(clientIp)) {
          logger.error("Error Ip Address: " + clientIp);
          return HttpResult.error("只允许在限定的地址登录。");
        }
      }

      // 处理登录应用ID
      String appCode = this.appSettings.getAppCode();
      if (StringUtils.hasText(payload.getAppCode())) {
        appCode = payload.getAppCode();
      }
      String appId = applicManager.findAppIdByCode(appCode);
      if (StringUtils.isBlank(appId)) {
        return HttpResult.error("指定的应用编号不存在。");
      }
      loginUser.setAppId(appId);

      if (LoginTypes.PWD.equals(payload.getType())) {
        if (!passwordEncoder.matches(payload.getPassword(), loginUser.getPassword())) {
          // 如果密码错误，要求输入验证码。
          imageVerifyHandler.setNeed(sessionId);
          // 记入错误重试策略。
          RetryResult retryResult = retryLimitStrategy.write(payload.getUsername());
          if (retryResult.isLocked()) {
            userManager.lockUser(loginUser.getUserId());
          }
          if (StringUtils.hasText(retryResult.getMessage())) {
            return HttpResult.error(retryResult.getMessage());
          } else {
            return HttpResult.error("提供的密码错误。");
          }
        } else {
          imageVerifyHandler.clean(sessionId);
          retryLimitStrategy.clean(payload.getUsername());
        }
      }
      loginUser.eraseCredentials();
    } catch (Exception ex) {
      imageVerifyHandler.setNeed(sessionId);
      eventGateway.publish(new LoginFailureEvent(this, ex));
      return HttpResult.error(ex.getMessage());
    }

    eventGateway.publish(new LoginSuccessEvent(this, loginUser));

    String newToken = jwtTokenUtil.createToken(loginUser);

    eventGateway.publish(new TokenCreatedEvent(this, loginUser, newToken));

    return HttpResult.okBody(newToken);
  }
}
