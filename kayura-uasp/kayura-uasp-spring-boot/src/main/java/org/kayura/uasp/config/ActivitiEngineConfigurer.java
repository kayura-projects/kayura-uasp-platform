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

import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.impl.history.HistoryLevel;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ActivitiEngineConfigurer implements ProcessEngineConfigurationConfigurer {

  private final List<ActivitiEventListener> eventListeners;

  public ActivitiEngineConfigurer(List<ActivitiEventListener> eventListeners) {
    this.eventListeners = eventListeners;
  }

  @Override
  public void configure(SpringProcessEngineConfiguration configuration) {
    configuration.setDbHistoryUsed(true);
    configuration.setHistoryLevel(HistoryLevel.FULL);
    if (eventListeners != null) {
      configuration.setEventListeners(eventListeners);
    }
  }
}
