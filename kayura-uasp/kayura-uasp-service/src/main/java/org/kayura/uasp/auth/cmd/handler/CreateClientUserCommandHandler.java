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

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.UserTypes;
import org.kayura.uasp.auth.cmd.CreateClientUserCommand;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.user.ClientUserPayload;
import org.kayura.uasp.user.ClientUserVo;
import org.kayura.utils.DateUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateClientUserCommandHandler implements CommandHandler<CreateClientUserCommand, HttpResult> {

  private final UserManager userManager;
  private final ModelMapper modelMapper;

  public CreateClientUserCommandHandler(UserManager userManager,
                                        ModelMapper modelMapper) {
    this.userManager = userManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateClientUserCommand command) {

    LoginUser loginUser = command.getLoginUser();
    ClientUserPayload payload = command.getPayload();

    if (userManager.selectCount(w -> w.eq(UserEntity::getUserName, payload.getClientNo())) > 0) {
      return HttpResult.error("客户端账号已经重复。");
    }

    UserEntity entity = UserEntity.create()
      .setUserId(userManager.nextId())
      .setUserType(UserTypes.CLIENT)
      .setUserName(payload.getClientNo())
      .setPassword(payload.getSecretKey())
      .setDisplayName(payload.getDisplayName())
      .setEnabled(payload.getEnabled())
      .setAccountExpire(payload.getAccountExpire())
      .setLocked(Boolean.FALSE)
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setRemark(payload.getRemark());
    userManager.insertOne(entity);

    ClientUserVo model = modelMapper.map(entity, ClientUserVo.class);
    return HttpResult.okBody(model);
  }

}
