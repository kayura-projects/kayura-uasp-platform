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
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.auth.cmd.UpdateTenantCommand;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.tenant.TenantPayload;
import org.kayura.utils.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class UpdateTenantCommandHandler implements CommandHandler<UpdateTenantCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final CompanyManager companyManager;
  private final UserManager userManager;
  private final PasswordEncoder passwordEncoder;

  public UpdateTenantCommandHandler(TenantManager tenantManager,
                                    CompanyManager companyManager,
                                    UserManager userManager,
                                    PasswordEncoder passwordEncoder) {
    this.tenantManager = tenantManager;
    this.companyManager = companyManager;
    this.userManager = userManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public HttpResult execute(UpdateTenantCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String tenantId = command.getTenantId();
    TenantPayload payload = command.getPayload();

    TenantEntity tenantEntity = tenantManager.selectById(tenantId);
    if (tenantEntity == null) {
      return HttpResult.error("要更新的租户记录不存在。");
    }

    if (!Objects.equals(payload.getCode(), tenantEntity.getCode()) &&
      companyManager.selectCount(w -> {
        w.eq(CompanyEntity::getCode, payload.getCode());
        w.eq(CompanyEntity::getType, CompanyTypes.Tenant);
      }) > 0) {
      return HttpResult.error("公司编号已经被占用。");
    }

    if (!Objects.equals(payload.getName(), tenantEntity.getName()) &&
      companyManager.selectCount(w -> {
        w.eq(CompanyEntity::getShortName, payload.getName());
        w.eq(CompanyEntity::getType, CompanyTypes.Tenant);
      }) > 0) {
      return HttpResult.error("公司简称已经被占用。");
    }

    // update tenant
    tenantEntity.setExpireTime(payload.getExpireTime());
    tenantEntity.setRemark(payload.getRemark());
    tenantManager.updateById(tenantEntity.getTenantId(), tenantEntity);

    // update company
    CompanyEntity companyEntity = companyManager.selectById(tenantEntity.getTenantId());
    companyEntity.setCode(payload.getCode());
    companyEntity.setShortName(payload.getName());
    companyManager.updateById(companyEntity.getCompanyId(), companyEntity);

    // update user
    payload.setAdminId(tenantEntity.getAdminId());
    Result updateResult = this.autoUpdateAdmin(payload, loginUser);
    if (updateResult.isFail()) {
      return HttpResult.of(updateResult);
    }

    return HttpResult.ok();
  }

  protected Result autoUpdateAdmin(TenantPayload payload, LoginUser loginUser) {

    UserEntity entity = userManager.selectById(payload.getAdminId());
    if (entity == null) {
      return Result.fail("要更新的管理员不存在。");
    }

    // 若改变账号名，将检查是否占用。
    if (!Objects.equals(entity.getUserName(), payload.getAdminCode()) &&
      userManager.selectCount(w -> {
        w.eq(UserEntity::getUserName, payload.getAdminCode());
        w.notEq(UserEntity::getUserId, payload.getAdminId());
      }) > 0) {
      return Result.fail("账号名已经被占用。");
    }

    // 如果指定了手机号是否被占用。
    if (StringUtils.hasText(payload.getAdminMobile())) {
      if (!Objects.equals(entity.getMobile(), payload.getAdminMobile()) &&
        userManager.selectCount(w -> {
          w.eq(UserEntity::getMobile, payload.getAdminMobile());
          w.notEq(UserEntity::getUserId, payload.getAdminId());
        }) > 0) {
        return Result.fail("手机号已经被占用。");
      }
    }

    // 如果指定了邮箱是否被占用。
    if (StringUtils.hasText(payload.getAdminEmail())) {
      if (!Objects.equals(entity.getEmail(), payload.getAdminEmail()) &&
        userManager.selectCount(w -> {
          w.eq(UserEntity::getEmail, payload.getAdminEmail());
          w.notEq(UserEntity::getUserId, payload.getAdminId());
        }) > 0) {
        return Result.fail("邮箱已经被占用。");
      }
    }

    entity.setUserName(payload.getAdminCode());
    entity.setDisplayName(payload.getAdminName());
    if (StringUtils.hasText(payload.getAdminPwd())) {
      String encode = this.passwordEncoder.encode(payload.getAdminPwd());
      entity.setPassword(encode);
    }
    entity.setEmail(payload.getAdminEmail());
    entity.setMobile(payload.getAdminMobile());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    userManager.updateById(entity.getUserId(), entity);

    return Result.ok();
  }
}
