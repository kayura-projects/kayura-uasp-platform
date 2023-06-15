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
