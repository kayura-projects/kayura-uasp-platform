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

package org.kayura.uasp.workflow.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.SelectItem;
import org.kayura.uasp.workflow.cmd.ChooseInnerExprCommand;
import org.kayura.uasp.workflow.handler.InnerExprManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChooseInnerExprCommandHandler implements CommandHandler<ChooseInnerExprCommand, HttpResult> {

  private final InnerExprManager innerExprManager;

  public ChooseInnerExprCommandHandler(InnerExprManager innerExprManager) {
    this.innerExprManager = innerExprManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseInnerExprCommand command) {

    List<SelectItem> selectItems = innerExprManager.queryHandlers()
      .stream()
      .map(m -> SelectItem.create().setId(m.getId()).setText(m.getName()))
      .collect(Collectors.toList());
    return HttpResult.okBody(selectItems);
  }
}
