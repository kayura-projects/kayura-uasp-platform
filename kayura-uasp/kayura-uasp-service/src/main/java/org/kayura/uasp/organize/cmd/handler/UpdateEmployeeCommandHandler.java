/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.Result;
import org.kayura.uasp.auth.entity.UserAvatarEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserAvatarManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.dev.manage.ModuleManager;
import org.kayura.uasp.organize.EmployeePayload;
import org.kayura.uasp.organize.cmd.UpdateEmployeeCommand;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.manage.EmployeeManager;
import org.kayura.utils.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UpdateEmployeeCommandHandler implements CommandHandler<UpdateEmployeeCommand, HttpResult> {

  private final EmployeeManager employeeManager;
  private final UserManager userManager;
  public final ModuleManager moduleManager;
  private final UserAvatarManager avatarManager;
  private final PasswordEncoder passwordEncoder;

  public UpdateEmployeeCommandHandler(EmployeeManager employeeManager,
                                      UserManager userManager,
                                      ModuleManager moduleManager,
                                      UserAvatarManager avatarManager,
                                      PasswordEncoder passwordEncoder) {
    this.employeeManager = employeeManager;
    this.userManager = userManager;
    this.moduleManager = moduleManager;
    this.avatarManager = avatarManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public HttpResult execute(UpdateEmployeeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    EmployeePayload payload = command.getPayload();
    String employeeId = Optional.ofNullable(command.getEmployeeId()).orElse(payload.getEmployeeId());

    EmployeeEntity entity = employeeManager.selectById(employeeId);
    if (entity == null) {
      return HttpResult.error("更新的员工不存在。");
    }

    UserEntity user = userManager.selectById(employeeId);
    if (user == null) {
      return HttpResult.error("更新的员工不存在。");
    }

    Result result = this.updatePretreatment(payload, entity, user);
    if (result.isFail()) {
      return HttpResult.of(result);
    }

    this.saveHistoryAvatar(employeeId, payload, user, loginUser);

    this.updateEmployee(entity, payload, loginUser);

    this.updateUser(employeeId, payload, loginUser);

    return HttpResult.ok();
  }

  protected Result updatePretreatment(EmployeePayload payload, EmployeeEntity employee, UserEntity user) {

    if (!StringUtils.equals(payload.getUserName(), user.getUserName()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getUserName())) > 0) {
      return Result.fail("该账号已被注册。");
    }

    if (StringUtils.hasText(payload.getMobile()) &&
      !StringUtils.equals(payload.getMobile(), user.getMobile()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getMobile, payload.getMobile())) > 0) {
      return Result.fail("该手机号已被注册。");
    }

    if (StringUtils.hasText(payload.getEmail()) &&
      !StringUtils.equals(payload.getEmail(), user.getEmail()) &&
      userManager.selectCount(w -> w.eq(UserEntity::getEmail, payload.getEmail())) > 0) {
      return Result.fail("该邮箱已被注册。");
    }

    if (StringUtils.hasText(payload.getJobNo()) &&
      !StringUtils.equals(payload.getJobNo(), employee.getJobNo()) &&
      employeeManager.selectCount(w -> {
        w.eq(EmployeeEntity::getJobNo, payload.getJobNo());
        w.eq(EmployeeEntity::getCompanyId, payload.getCompanyId());
      }) > 0) {
      return Result.fail("该工号已被占用。");
    }

    return Result.ok();
  }

  protected void updateEmployee(EmployeeEntity entity, EmployeePayload payload, LoginUser loginUser) {

    entity.setJobNo(payload.getJobNo());
    entity.setRealName(payload.getRealName());
    entity.setBirthday(payload.getBirthday());
    entity.setOrigin(payload.getOrigin());
    entity.setNation(payload.getNation());
    entity.setSex(payload.getSex());
    entity.setEducation(payload.getEducation());
    entity.setHeight(payload.getHeight());
    entity.setWeight(payload.getWeight());
    entity.setMarital(payload.getMarital());
    entity.setIdCard(payload.getIdCard());
    entity.setAddress(payload.getAddress());
    entity.setEnterDate(payload.getEnterDate());
    entity.setOverDate(payload.getOverDate());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    if (payload.getStatus() != null) {
      entity.setStatus(payload.getStatus());
    }
    entity.setRemark(payload.getRemark());
    employeeManager.updateById(entity.getEmployeeId(), entity);
  }

  protected void updateUser(String employeeId, EmployeePayload payload, LoginUser loginUser) {

    UserEntity entity = userManager.selectById(employeeId);

    entity.setUserName(payload.getUserName());
    entity.setDisplayName(payload.getDisplayName());
    entity.setAvatar(payload.getAvatar());
    if (payload.getUserType() != null) {
      entity.setUserType(payload.getUserType());
    }
    if (StringUtils.hasText(payload.getPassword())) {
      entity.setPassword(this.passwordEncoder.encode(payload.getPassword()));
    }
    entity.setMobile(payload.getMobile());
    entity.setEmail(payload.getEmail());
    entity.setAccountExpire(payload.getAccountExpire());
    entity.setPasswordExpire(payload.getPasswordExpire());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(LocalDateTime.now());
    entity.setRemark(payload.getRemark());
    userManager.updateById(employeeId, entity);
  }

  protected void saveHistoryAvatar(String employeeId, EmployeePayload payload, UserEntity user, LoginUser loginUser) {

    if (!Objects.equals(user.getAvatar(), payload.getAvatar())) {
      List<UserAvatarEntity> list = avatarManager.selectList(w -> {
        w.eq(UserAvatarEntity::getPhotoId, payload.getAvatar());
        w.eq(UserAvatarEntity::getUserId, employeeId);
      });
      if (!list.isEmpty()) {
        for (UserAvatarEntity row : list) {
          avatarManager.updateByWhere(w -> {
            w.set(UserAvatarEntity::getUpdaterId, loginUser.getUserId());
            w.set(UserAvatarEntity::getUpdateTime, LocalDateTime.now());
            w.eq(UserAvatarEntity::getAvatarId, row.getAvatarId());
          });
        }
      } else {
        UserAvatarEntity uv = UserAvatarEntity.create()
          .setAvatarId(avatarManager.nextId())
          .setUserId(employeeId)
          .setUpdaterId(loginUser.getUserId())
          .setPhotoId(payload.getAvatar());
        avatarManager.insertOne(uv);
      }
    }
  }
}
