package org.kayura.uasp.activiti.cmd;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;

public class DeleteProcessCmd implements Command<Void> {

  public static final String FORM_DELETED = "delete the business form";

  private String instanceId;
  private String deleteReason;

  public static DeleteProcessCmd create() {
    return new DeleteProcessCmd();
  }

  @Override
  public Void execute(CommandContext commandContext) {

    Context.getProcessEngineConfiguration()
      .getExecutionEntityManager()
      .deleteProcessInstance(instanceId, deleteReason, true);
    return null;
  }

  public DeleteProcessCmd setInstanceId(String instanceId) {
    this.instanceId = instanceId;
    return this;
  }

  public DeleteProcessCmd setDeleteReason(String deleteReason) {
    this.deleteReason = deleteReason;
    return this;
  }
}
