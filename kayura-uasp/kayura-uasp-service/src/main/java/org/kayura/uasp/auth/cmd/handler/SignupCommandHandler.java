package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.event.EventGateway;
import org.kayura.except.ExceptUtils;
import org.kayura.security.vcode.SmsVerifyHandler;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.cmd.SignupCommand;
import org.kayura.uasp.auth.entity.SignupEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.SignupManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.signup.SignupPayload;
import org.kayura.uasp.signup.SignupStatus;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class SignupCommandHandler implements CommandHandler<SignupCommand, HttpResult> {

  private final UserManager userManager;
  private final SignupManager signupManager;
  private final SmsVerifyHandler smsVerifyHandler;
  private final PasswordEncoder passwordEncoder;
  private final EventGateway eventGateway;

  public SignupCommandHandler(UserManager userManager,
                              SmsVerifyHandler smsVerifyHandler,
                              SignupManager signupManager,
                              PasswordEncoder passwordEncoder,
                              EventGateway eventGateway) {
    this.userManager = userManager;
    this.smsVerifyHandler = smsVerifyHandler;
    this.signupManager = signupManager;
    this.passwordEncoder = passwordEncoder;
    this.eventGateway = eventGateway;
  }

  @Transactional
  public HttpResult execute(SignupCommand command) {

    SignupPayload payload = command.getPayload();

    // 验证用户名、手机号是否存在
    if (StringUtils.hasText(payload.getUserName()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getUserName())) > 0) {
      ExceptUtils.business("该用户名已经存在。");
    }

    if (StringUtils.hasText(payload.getMobile()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getMobile, payload.getMobile())) > 0) {
      ExceptUtils.business("该手机号已经注册。");
    }

    // 检查短信验证码，是否正确
    smsVerifyHandler.verify(payload.getMobile(), payload.getUcode(), payload.getVcode());

    // 添加注册记录
    String secret = passwordEncoder.encode(payload.getPassword());

    SignupEntity entity = SignupEntity.create()
      .setSignupId(signupManager.nextId())
      .setMobile(payload.getMobile())
      .setCreateTime(LocalDateTime.now())
      .setStatus(SignupStatus.APPLYING)
      .setPassword(secret);

    if (StringUtils.isBlank(payload.getUserName())) {
      entity.setUserName(RandomStringUtils.randomAlphabetic(24));
    }
    if (StringUtils.isBlank(payload.getDisplayName())) {
      entity.setDisplayName(RandomStringUtils.randomAlphabetic(16));
    }

    signupManager.insertOne(entity);

    return HttpResult.ok();
  }
}
