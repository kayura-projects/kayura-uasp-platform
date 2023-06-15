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
