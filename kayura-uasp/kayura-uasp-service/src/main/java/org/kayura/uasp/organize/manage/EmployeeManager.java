package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.EmployeeEntity;
import org.kayura.uasp.organize.mapper.EmployeeMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class EmployeeManager extends CrudManagerImpl<EmployeeMapper, EmployeeEntity> {

  protected EmployeeManager(EmployeeMapper baseMapper) {
    super(baseMapper);
  }

  public Map<String, String> queryIdNameMap(List<String> ids) {

    Map<String, String> nameMap = this.selectList(w -> {
      w.select(EmployeeEntity::getEmployeeId);
      w.select(EmployeeEntity::getRealName);
      w.in(EmployeeEntity::getEmployeeId, ids);
    }).stream().collect(
      Collectors.toMap(EmployeeEntity::getEmployeeId, EmployeeEntity::getRealName)
    );
    return nameMap;
  }
}
