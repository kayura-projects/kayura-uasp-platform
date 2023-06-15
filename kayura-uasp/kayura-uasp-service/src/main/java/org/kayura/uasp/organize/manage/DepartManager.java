package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.mapper.DepartMapper;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class DepartManager extends CrudManagerImpl<DepartMapper, DepartEntity> {

  public static final String DELETE_EXISTS_CHILDREN = "存在子部门，不允许删除。";

  protected DepartManager(DepartMapper baseMapper) {
    super(baseMapper);
  }

  public Result deleteForPayload(IdPayload payload) {

    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      if (this.selectCount(w -> w.in(DepartEntity::getParentId, payload.getIds())) > 0) {
        return Result.fail(DELETE_EXISTS_CHILDREN);
      }
      this.deleteByIds(payload.getIds());
    } else if (StringUtils.isNotBlank(payload.getId())) {
      if (this.selectCount(w -> w.eq(DepartEntity::getParentId, payload.getId())) > 0) {
        return Result.fail(DELETE_EXISTS_CHILDREN);
      }
      this.deleteById(payload.getId());
    }
    return Result.ok();
  }
}
