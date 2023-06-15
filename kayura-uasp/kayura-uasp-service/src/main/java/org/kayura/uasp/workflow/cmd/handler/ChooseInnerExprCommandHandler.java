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
