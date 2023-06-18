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
