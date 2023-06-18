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
