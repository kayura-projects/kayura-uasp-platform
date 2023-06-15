/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;

public class GetBpmnXmlCommand extends Command {

  private String flowId;
  private String modelId;
  private String defineId;

  public String getFlowId() {
    return flowId;
  }

  public GetBpmnXmlCommand setFlowId(String flowId) {
    this.flowId = flowId;
    return this;
  }

  public String getModelId() {
    return modelId;
  }

  public GetBpmnXmlCommand setModelId(String modelId) {
    this.modelId = modelId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public GetBpmnXmlCommand setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }
}
