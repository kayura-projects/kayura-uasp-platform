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
