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
