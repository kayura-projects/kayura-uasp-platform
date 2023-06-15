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
import org.kayura.uasp.form.FormDefinePayload;

public class CreateFormDefineCommand extends Command {

  private FormDefinePayload payload;

  public FormDefinePayload getPayload() {
    return payload;
  }

  public CreateFormDefineCommand setPayload(FormDefinePayload payload) {
    this.payload = payload;
    return this;
  }
}
