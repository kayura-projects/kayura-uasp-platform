/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
