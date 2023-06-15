package org.kayura.uasp.activiti.mapper;

import org.kayura.uasp.activiti.entity.HistoryProcInstEntity;
import org.kayura.uasp.activiti.model.ProcInstCount;
import org.kayura.mybatis.mapper.SelectMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryProcInstMapper extends SelectMapper<HistoryProcInstEntity> {

  List<ProcInstCount> collectProcInst(@Param("processKeys") List<String> processKeys);

}
