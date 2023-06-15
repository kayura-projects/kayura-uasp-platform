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
