package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.Result;
import org.kayura.uasp.common.IdPayload;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.mapper.CompanyMapper;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class CompanyManager extends CrudManagerImpl<CompanyMapper, CompanyEntity> {

  private static final String ERROR_EXISTS_CHILDREN = "存在子公司，不允许删除。";

  protected CompanyManager(CompanyMapper baseMapper) {
    super(baseMapper);
  }

  public Result deleteForPayload(IdPayload payload) {

    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      if (this.selectCount(w -> w.in(CompanyEntity::getParentId, payload.getIds())) > 0) {
        return Result.fail(ERROR_EXISTS_CHILDREN);
      }
      this.deleteByIds(payload.getIds());
    } else if (StringUtils.hasText(payload.getId())) {
      if (this.selectCount(w -> w.eq(CompanyEntity::getParentId, payload.getId())) > 0) {
        return Result.fail(ERROR_EXISTS_CHILDREN);
      }
      this.deleteById(payload.getId());
    }
    return Result.ok();
  }

}
