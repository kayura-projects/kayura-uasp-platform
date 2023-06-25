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
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.IdName;
import org.kayura.type.TreeNode;
import org.kayura.uasp.workflow.cmd.ChooseFormTreeCommand;
import org.kayura.uasp.workflow.entity.FormDefineEntity;
import org.kayura.uasp.workflow.manage.FormDefineManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChooseFormTreeCommandHandler implements CommandHandler<ChooseFormTreeCommand, HttpResult> {

  private final FormDefineManager defineManager;

  public ChooseFormTreeCommandHandler(FormDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseFormTreeCommand command) {

    LoginUser loginUser = command.getLoginUser();

    List<FormDefineEntity> list = defineManager.selectList(w -> {
      w.select(FormDefineEntity::getAppId);
      w.select(FormDefineEntity::getAppName);
      w.select(FormDefineEntity::getCategory);
      w.select(FormDefineEntity::getFormId);
      w.select(FormDefineEntity::getCode);
      w.select(FormDefineEntity::getName);
      if (loginUser.hasTenantUser()) {
        w.eq(FormDefineEntity::getAppId, loginUser.getAppId());
      }
      w.eq(FormDefineEntity::getStatus, DataStatus.Valid);
      w.orderByAsc(FormDefineEntity::getSort);
    });

    List<TreeNode> result = new ArrayList<>();

    List<IdName> apps = list.stream().map(m ->
      IdName.create().setId(m.getAppId()).setName(m.getAppName())
    ).distinct().toList();

    for (IdName app : apps) {

      TreeNode appNode = TreeNode.create()
        .setId(app.getId())
        .setText(app.getName())
        .setType("APP");
      result.add(appNode);

      List<FormDefineEntity> listByApp = list.stream()
        .filter(x -> x.getAppId().equals(app.getId())).toList();
      List<String> categories = listByApp.stream()
        .map(FormDefineEntity::getCategory)
        .distinct().toList();
      for (String cat : categories) {

        TreeNode catNode = TreeNode.create()
          .setId(cat)
          .setText(cat)
          .setType("CAT");
        appNode.addChildren(catNode);

        List<FormDefineEntity> collect = listByApp.stream()
          .filter(x -> StringUtils.equalsIgnoreCase(cat, x.getCategory())).toList();
        for (FormDefineEntity e : collect) {
          TreeNode node = TreeNode.create()
            .setId(e.getFormId())
            .setCode(e.getCode())
            .setText(e.getName())
            .setType("FORM");
          catNode.addChildren(node);
        }
      }
    }

    return HttpResult.okBody(result);
  }
}
