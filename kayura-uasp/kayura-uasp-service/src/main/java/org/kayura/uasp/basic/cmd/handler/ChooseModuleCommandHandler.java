/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
