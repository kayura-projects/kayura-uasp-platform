/*
 * 版权所属 2019 Liang.Xia 及 原作者
 * 您可以在 Apache License 2.0 版下获得许可副本，
 * 同时必须保证分发的本软件是在“原始”的基础上分发的。
 * 除非适用法律要求或书面同意。
 * http://www.apache.org/licenses/LICENSE-2.0
 * 请参阅许可证中控制权限和限制的特定语言。
 */

package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.common.IdPayload;

public class DeleteFileFolderCommand extends Command {

  private String folderId;
  private IdPayload payload;

  public String getFolderId() {
    return folderId;
  }

  public DeleteFileFolderCommand setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public IdPayload getPayload() {
    return payload;
  }

  public DeleteFileFolderCommand setPayload(IdPayload payload) {
    this.payload = payload;
    return this;
  }
}
