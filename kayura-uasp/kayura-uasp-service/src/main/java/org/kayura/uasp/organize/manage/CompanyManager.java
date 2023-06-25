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
