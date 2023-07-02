/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
    return this.handlers.stream().map(InnerExprHandler::getIdName).toList();
  }

}
