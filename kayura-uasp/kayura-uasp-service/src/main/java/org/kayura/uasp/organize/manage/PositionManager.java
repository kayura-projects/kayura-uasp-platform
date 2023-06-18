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

package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.mapper.PositionMapper;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class PositionManager extends CrudManagerImpl<PositionMapper, PositionEntity> {

  protected PositionManager(PositionMapper baseMapper) {
    super(baseMapper);
  }

  public Result deleteForPayload(IdPayload payload) {

    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      this.deleteByIds(payload.getIds());
    } else if (StringUtils.isNotBlank(payload.getId())) {
      this.deleteById(payload.getId());
    }

    return Result.ok();
  }
}
