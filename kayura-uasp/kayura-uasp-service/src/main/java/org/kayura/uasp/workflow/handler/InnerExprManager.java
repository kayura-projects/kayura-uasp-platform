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
