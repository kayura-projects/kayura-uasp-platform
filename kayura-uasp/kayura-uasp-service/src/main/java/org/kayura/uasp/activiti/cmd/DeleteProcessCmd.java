/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
