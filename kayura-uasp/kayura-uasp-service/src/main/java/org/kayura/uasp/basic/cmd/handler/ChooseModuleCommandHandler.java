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

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.TreeNode;
import org.kayura.uasp.basic.cmd.ChooseModuleCommand;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.dev.manage.ModuleDefineManager;
import org.kayura.uasp.func.ModuleTypes;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class ChooseModuleCommandHandler implements CommandHandler<ChooseModuleCommand, HttpResult> {

  private final ModuleDefineManager defineManager;

  public ChooseModuleCommandHandler(ModuleDefineManager defineManager) {
    this.defineManager = defineManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseModuleCommand command) {

    String appId = command.getAppId();
    Set<ModuleTypes> types = command.getTypes();

    List<ModuleDefineEntity> list = defineManager.selectList(w -> {
      if (StringUtils.hasText(appId)) {
        w.eq(ModuleDefineEntity::getAppId, appId);
      }
      if (types != null && types.size() > 0) {
        w.in(ModuleDefineEntity::getType, types);
      }
    });

    List<TreeNode> rootNodes = new ArrayList<>(1);

    // 从根节点开始加载
    List<ModuleDefineEntity> rootModules = list.stream().filter(x -> x.getParentId() == null).toList();
    for (ModuleDefineEntity md : rootModules) {
      TreeNode rootNode = TreeNode.create()
        .setId(md.getModuleId())
        .setCode(md.getCode())
        .setText(md.getName())
        .setType(md.getType().toString());
      rootNodes.add(rootNode);
      createChildNodes(rootNode, list);
    }

    return HttpResult.okBody(rootNodes);
  }

  void createChildNodes(TreeNode treeNode, List<ModuleDefineEntity> all) {
    List<ModuleDefineEntity> collect = all.stream().filter(x -> treeNode.getId().equals(x.getParentId())).toList();
    for (ModuleDefineEntity cc : collect) {
      TreeNode node = TreeNode.create()
        .setId(cc.getModuleId())
        .setCode(cc.getCode())
        .setText(cc.getName())
        .setType(cc.getType().toString())
        .setIcon(cc.getIcon());
      treeNode.addChildren(node);
      createChildNodes(node, all);
    }
  }

}
