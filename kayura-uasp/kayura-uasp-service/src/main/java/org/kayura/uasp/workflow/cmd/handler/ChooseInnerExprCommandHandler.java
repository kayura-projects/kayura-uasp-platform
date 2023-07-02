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
      .toList();
    return HttpResult.okBody(selectItems);
  }
}
