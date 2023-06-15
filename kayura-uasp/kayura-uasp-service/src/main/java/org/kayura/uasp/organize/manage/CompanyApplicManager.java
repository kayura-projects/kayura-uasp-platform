package org.kayura.uasp.organize.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.uasp.organize.entity.CompanyApplicEntity;
import org.kayura.uasp.organize.mapper.CompanyApplicMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyApplicManager extends CrudManagerImpl<CompanyApplicMapper, CompanyApplicEntity> {

  protected CompanyApplicManager(CompanyApplicMapper baseMapper) {
    super(baseMapper);
  }

}
