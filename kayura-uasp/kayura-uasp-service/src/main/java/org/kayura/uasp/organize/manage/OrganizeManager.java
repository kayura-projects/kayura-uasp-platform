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

package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.SelectManagerImpl;
import org.kayura.uasp.organize.entity.OrganizeEntity;
import org.kayura.uasp.organize.mapper.OrganizeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrganizeManager extends SelectManagerImpl<OrganizeMapper, OrganizeEntity> {

  protected OrganizeManager(OrganizeMapper baseMapper) {
    super(baseMapper);
  }

  public Map<String, String> queryIdNameMap(List<String> ids) {

    Map<String, String> nameMap = this.selectList(w -> {
      w.select(OrganizeEntity::getId);
      w.select(OrganizeEntity::getName);
      w.in(OrganizeEntity::getId, ids);
    }).stream().collect(
      Collectors.toMap(OrganizeEntity::getId, OrganizeEntity::getName)
    );
    return nameMap;
  }
}
