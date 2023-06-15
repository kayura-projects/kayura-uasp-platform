package org.kayura.skeleton.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.skeleton.entity.BlankEntity;
import org.kayura.skeleton.mapper.BlankMapper;
import org.springframework.stereotype.Component;

@Component
public class BlankManager extends CrudManagerImpl<BlankMapper, BlankEntity> {

  protected BlankManager(BlankMapper baseMapper) {
    super(baseMapper);
  }

}