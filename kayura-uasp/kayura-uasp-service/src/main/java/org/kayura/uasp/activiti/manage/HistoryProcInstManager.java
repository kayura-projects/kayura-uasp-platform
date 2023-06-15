package org.kayura.uasp.activiti.manage;

import org.kayura.mybatis.manager.impl.SelectManagerImpl;
import org.kayura.uasp.activiti.entity.HistoryProcInstEntity;
import org.kayura.uasp.activiti.mapper.HistoryProcInstMapper;
import org.kayura.uasp.activiti.model.ProcInstCount;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoryProcInstManager extends SelectManagerImpl<HistoryProcInstMapper, HistoryProcInstEntity> {

  private final HistoryProcInstMapper thisMapper;

  protected HistoryProcInstManager(HistoryProcInstMapper baseMapper) {
    super(baseMapper);
    this.thisMapper = baseMapper;
  }

  public List<ProcInstCount> collectProcInst(List<String> processKeys) {
    return thisMapper.collectProcInst(processKeys);
  }

}
