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

package org.kayura.uasp.workflow.handler;

import org.kayura.type.IdName;
import org.kayura.uasp.workflow.InnerExprArgs;
import org.kayura.uasp.workflow.InnerExprHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InnerExprManager {

  private final List<InnerExprHandler> handlers;

  public InnerExprManager(
    ObjectProvider<List<InnerExprHandler>> innerExprHandlers
  ) {
    this.handlers = innerExprHandlers.getIfAvailable();
  }

  public boolean execute(String exprId, InnerExprArgs args) {

    if (handlers != null) {
      for (InnerExprHandler handler : this.handlers) {
        if (handler.getIdName().getId().equals(exprId)) {
          if (handler.execute(args)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public List<IdName> queryHandlers() {
    return this.handlers.stream().map(InnerExprHandler::getIdName).collect(Collectors.toList());
  }

}
