package org.kayura.uasp.config;

import org.kayura.mybatis.autoconfigure.MbConfigurationCustomizer;
import org.kayura.mybatis.session.MbConfiguration;
import org.kayura.mybatis.typehandler.JsonTypeHandler;
import org.kayura.uasp.dict.DictFieldList;
import org.kayura.uasp.workflow.FlowLabels;
import org.kayura.uasp.workflow.FormulaGroups;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfigurer implements MbConfigurationCustomizer {

  @Override
  public void customize(MbConfiguration configuration) {
    TypeHandlerRegistry registry = configuration.getTypeHandlerRegistry();
    registry.register(DictFieldList.class, JsonTypeHandler.class);
    registry.register(FormulaGroups.class, JsonTypeHandler.class);
    registry.register(FlowLabels.class, JsonTypeHandler.class);
  }
}
