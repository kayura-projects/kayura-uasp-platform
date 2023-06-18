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
