package org.kayura.uasp.config;

import org.activiti.engine.delegate.event.ActivitiEventListener;
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
    if (eventListeners != null) {
      configuration.setEventListeners(eventListeners);
    }
  }
}
