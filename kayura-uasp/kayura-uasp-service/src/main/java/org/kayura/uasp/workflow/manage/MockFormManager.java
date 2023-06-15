package org.kayura.uasp.workflow.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.workflow.entity.MockFormEntity;
import org.kayura.uasp.workflow.mapper.MockFormMapper;
import org.springframework.stereotype.Component;

@Component
public class MockFormManager extends CrudManagerImpl<MockFormMapper, MockFormEntity> {

  protected MockFormManager(MockFormMapper baseMapper) {
    super(baseMapper);
  }

}
