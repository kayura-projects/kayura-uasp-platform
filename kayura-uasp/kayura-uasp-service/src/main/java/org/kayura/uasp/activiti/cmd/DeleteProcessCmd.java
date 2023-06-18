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
