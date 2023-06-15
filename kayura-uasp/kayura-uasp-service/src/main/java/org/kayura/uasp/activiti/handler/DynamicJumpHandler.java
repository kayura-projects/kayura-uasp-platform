package org.kayura.uasp.activiti.handler;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

import java.util.Map;

public interface DynamicJumpHandler {
  void handle(TaskEntity currentTask, String targetId, Map<String, Object> variables);
}
