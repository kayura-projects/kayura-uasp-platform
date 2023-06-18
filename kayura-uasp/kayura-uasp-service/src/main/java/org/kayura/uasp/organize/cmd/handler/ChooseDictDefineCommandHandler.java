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

package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.IdName;
import org.kayura.type.TreeNode;
import org.kayura.type.UsableStatus;
import org.kayura.uasp.dev.entity.ApplicEntity;
import org.kayura.uasp.dev.manage.ApplicManager;
import org.kayura.uasp.basic.entity.DictDefineEntity;
import org.kayura.uasp.basic.manage.DictDefineManager;
import org.kayura.uasp.dict.DictScopes;
import org.kayura.uasp.dict.DictTypes;
import org.kayura.uasp.organize.cmd.ChooseDictDefineCommand;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChooseDictDefineCommandHandler implements CommandHandler<ChooseDictDefineCommand, HttpResult> {

  private final ApplicManager applicManager;
  private final DictDefineManager defineManager;

  public ChooseDictDefineCommandHandler(ApplicManager applicManager,
                                        DictDefineManager defineManager) {
    this.applicManager = applicManager;
    this.defineManager = defineManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseDictDefineCommand command) {

    LoginUser loginUser = command.getLoginUser();

    List<TreeNode> treeNodes = new ArrayList<>();
    List<IdName> apps = applicManager.selectList(w -> {
      w.select(ApplicEntity::getAppId);
      w.select(ApplicEntity::getName);
      if (loginUser.hasTenantUser()) {
        w.eq(ApplicEntity::getAppId, loginUser.getAppId());
      }
      w.eq(ApplicEntity::getStatus, UsableStatus.Valid);
    }).stream().map(m ->
      IdName.create().setId(m.getAppId()).setName(m.getName())
    ).distinct().toList();
    List<String> appIds = apps.stream().map(IdName::getId).collect(Collectors.toList());

    List<DictDefineEntity> defineList = defineManager.selectList(w -> {
      w.in(DictDefineEntity::getAppId, appIds);
      if (loginUser.hasTenantUser()) {
        if (StringUtils.hasText(loginUser.getTenantId())) {
          w.eq(DictDefineEntity::getScope, DictScopes.Business);
        }
      }
    });
    for (IdName app : apps) {
      TreeNode appNode = TreeNode.create().setId(app.getId()).setText(app.getName()).setType("APP");
      List<DictDefineEntity> appDefines = defineList.stream().filter(x -> x.getAppId().equals(app.getId())).collect(Collectors.toList());
      List<DictDefineEntity> rootDefines = appDefines.stream().filter(x -> StringUtils.isBlank(x.getParentId())).collect(Collectors.toList());
      List<TreeNode> children = buildChildrenDefine(rootDefines, appDefines);
      if (!children.isEmpty()) {
        appNode.setChildren(children);
      }
      treeNodes.add(appNode);
    }

    TreeNode root = new TreeNode();
    root.setId("ROOT");
    root.setText("所有数据字典");
    root.setChildren(treeNodes);
    return HttpResult.okBody(Collections.singletonList(root));
  }

  protected List<TreeNode> buildChildrenDefine(List<DictDefineEntity> defines, List<DictDefineEntity> allDefines) {

    List<TreeNode> treeNodes = new ArrayList<>();
    for (DictDefineEntity dd : defines) {

      TreeNode node = TreeNode.create()
        .setId(dd.getDefineId())
        .setText(dd.getName())
        .setType(dd.getType().getValue());
      treeNodes.add(node);

      if (DictTypes.Item.equals(dd.getType())) {
        node.addAttr("dataType", dd.getDataType().getValue());
        node.addAttr("extFields", dd.getExtFields());
      } else {
        List<DictDefineEntity> collect = allDefines.stream()
          .filter(x -> dd.getDefineId().equals(x.getParentId()))
          .collect(Collectors.toList());
        if (!collect.isEmpty()) {
          List<TreeNode> children = buildChildrenDefine(collect, allDefines);
          if (!children.isEmpty()) {
            node.setChildren(children);
          }
        }
      }
    }
    return treeNodes;
  }
}
