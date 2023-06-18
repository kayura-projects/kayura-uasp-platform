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
