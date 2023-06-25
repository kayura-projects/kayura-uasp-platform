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
        node.put("dataType", dd.getDataType().getValue());
        node.put("extFields", dd.getExtFields());
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
