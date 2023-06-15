package org.kayura.uasp.workflow.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.mapper.FormDefineMapper;
import org.springframework.stereotype.Component;

@Component
public class FormDefineManager extends CrudManagerImpl<FormDefineMapper, FormDefineEntity> {

  protected FormDefineManager(FormDefineMapper baseMapper) {
    super(baseMapper);
  }

}
