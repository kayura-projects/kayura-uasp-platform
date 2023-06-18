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

package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.CreateTenantCommand;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.tenant.TenantPayload;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateTenantCommandHandler implements CommandHandler<CreateTenantCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final TenantManager tenantManager;
  private final UserManager userManager;
  private final PasswordEncoder passwordEncoder;

  public CreateTenantCommandHandler(CompanyManager companyManager,
                                    TenantManager tenantManager,
                                    UserManager userManager,
                                    PasswordEncoder passwordEncoder) {
    this.companyManager = companyManager;
    this.tenantManager = tenantManager;
    this.userManager = userManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public HttpResult execute(CreateTenantCommand command) {

    LoginUser loginUser = command.getLoginUser();
    TenantPayload payload = command.getPayload();

    Result checkResult = this.checkValid(payload);
    if (checkResult.isFail()) {
      return HttpResult.of(checkResult);
    }

    String tenantId = tenantManager.nextId();
    String userId = userManager.nextId();

    TenantEntity tenantEntity = TenantEntity.create();
    tenantEntity.setTenantId(tenantId);
    tenantEntity.setExpireTime(payload.getExpireTime());
    tenantEntity.setAdminId(userId);
    tenantEntity.setRemark(payload.getRemark());

    this.autoCreateCompany(tenantId, payload, loginUser);

    tenantManager.insertOne(tenantEntity);

    payload.setAdminId(userId);
    this.autoCreateAdmin(tenantId, payload, loginUser);

    return HttpResult.ok();
  }

  protected Result checkValid(TenantPayload payload) {

    if (companyManager.selectCount(w -> {
      w.eq(CompanyEntity::getCode, payload.getCode());
      w.eq(CompanyEntity::getType, CompanyTypes.Tenant);
    }) > 0) {
      return Result.fail("编号已经被占用。");
    }

    if (companyManager.selectCount(w -> {
      w.eq(CompanyEntity::getShortName, payload.getName());
      w.eq(CompanyEntity::getType, CompanyTypes.Tenant);
    }) > 0) {
      return Result.fail("简称已经被占用。");
    }

    // mobile
    if (StringUtils.hasText(payload.getAdminMobile())) {
      if (userManager.selectCount(w -> w.eq(UserEntity::getMobile, payload.getAdminMobile())) > 0) {
        return Result.fail("手机号已经被占用。");
      }
    }

    // code
    if (StringUtils.hasText(payload.getAdminCode())) {
      if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getAdminCode())) > 0) {
        return Result.fail("账号名已经被占用。");
      }
    }

    // email;
    if (StringUtils.hasText(payload.getAdminEmail())) {
      if (userManager.selectCount(w -> w.eq(UserEntity::getEmail, payload.getAdminEmail())) > 0) {
        return Result.fail("邮箱地址已经被占用。");
      }
    }

    return Result.ok();
  }

  protected void autoCreateCompany(String tenantId, TenantPayload form, LoginUser loginUser) {

    CompanyEntity companyEntity = CompanyEntity.create()
      .setTenantId(tenantId)
      .setCompanyId(tenantId)
      .setCode(form.getCode())
      .setShortName(form.getName())
      .setFullName(form.getName())
      .setMobile(form.getAdminMobile())
      .setType(CompanyTypes.Tenant)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setStatus(DataStatus.Valid);
    companyManager.insertOne(companyEntity);
  }

  protected void autoCreateAdmin(String tenantId, TenantPayload payload, LoginUser loginUser) {

    UserEntity userEntity = UserEntity.create()
      .setUserId(payload.getAdminId())
      .setMobile(payload.getAdminMobile())
      .setUserType(UserTypes.ADMIN)
      .setTenantId(tenantId)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setEnabled(Boolean.TRUE)
      .setLocked(Boolean.FALSE);

    // userName
    if (StringUtils.hasText(payload.getAdminCode())) {
      userEntity.setUserName(payload.getAdminCode());
    } else {
      if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getCode())) > 0) {
        userEntity.setUserName(payload.getCode() + "_" + RandomStringUtils.randomAlphabetic(4));
      } else {
        userEntity.setUserName(payload.getCode());
      }
    }

    // email
    if (StringUtils.hasText(payload.getAdminEmail())) {
      userEntity.setEmail(payload.getAdminEmail());
    }

    // password
    if (StringUtils.hasText(payload.getAdminPwd())) {
      userEntity.setPassword(passwordEncoder.encode(payload.getAdminPwd()));
    } else {
      userEntity.setPassword(passwordEncoder.encode(RandomStringUtils.randomAlphabetic(16)));
    }

    // displayName
    if (StringUtils.hasText(payload.getAdminName())) {
      userEntity.setDisplayName(payload.getAdminName());
    } else {
      userEntity.setDisplayName(payload.getName());
    }

    userManager.insertOne(userEntity);
  }
}
