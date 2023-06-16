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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.DepartPayload;
import org.kayura.uasp.organize.cmd.UpdateDepartCommand;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.DepartLeaderEntity;
import org.kayura.uasp.organize.manage.DepartLeaderManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UpdateDepartCommandHandler implements CommandHandler<UpdateDepartCommand, HttpResult> {

  private final DepartManager departManager;
  private final DepartLeaderManager departLeaderManager;

  public UpdateDepartCommandHandler(DepartManager departManager,
                                    DepartLeaderManager departLeaderManager) {
    this.departManager = departManager;
    this.departLeaderManager = departLeaderManager;
  }

  @Transactional
  public HttpResult execute(UpdateDepartCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DepartPayload payload = command.getPayload();
    String departId = command.getDepartId();

    DepartEntity entity = departManager.selectById(departId);
    if (entity == null) {
      return HttpResult.error("更新的数据不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) &&
      !payload.getCode().equalsIgnoreCase(entity.getCode()) &&
      departManager.selectCount(w -> {
        w.eq(DepartEntity::getCode, payload.getCode());
        w.notEq(DepartEntity::getDepartId, departId);
      }) > 0) {
      return HttpResult.error("编号已经存在。");
    }

    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setSort(payload.getSort());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(DateUtils.now());
    departManager.updateById(departId, entity);

    // 添加领导数据，简单处理
    departLeaderManager.deleteByWhere(w -> w.eq(DepartLeaderEntity::getDepartId, departId));
    if (CollectionUtils.isNotEmpty(payload.getLeaders())) {
      List<DepartLeaderEntity> collect = payload.getLeaders().stream().map(m ->
        DepartLeaderEntity.create()
          .setDepartId(entity.getDepartId())
          .setLeaderId(m.getLeaderId())
          .setDuty(m.getDuty())
      ).collect(Collectors.toList());
      departLeaderManager.insertBatch(collect);
    }

    return HttpResult.ok();
  }

}
