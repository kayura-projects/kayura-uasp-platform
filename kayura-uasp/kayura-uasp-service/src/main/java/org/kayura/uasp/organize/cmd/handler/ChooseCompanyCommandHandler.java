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
import org.kayura.type.*;
import org.kayura.uasp.organize.cmd.ChooseCompanyCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ChooseCompanyCommandHandler implements CommandHandler<ChooseCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final ModelMapper modelMapper;

  public ChooseCompanyCommandHandler(CompanyManager companyManager,
                                     ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseCompanyCommand command) {

    OutputTypes output = command.getOutput();
    CompanyTypes type = command.getType();
    String tenantId = command.getTenantId();
    String parentId = command.getParentId();
    String categoryId = command.getCategoryId();
    OrderByClause orderByClause = command.getOrderByClause();

    List<CompanyEntity> entities = companyManager.selectList(w -> {
      if (type != null) {
        w.eq(CompanyEntity::getType, type);
      }
      if (StringUtils.hasText(tenantId)) {
        w.eq(CompanyEntity::getTenantId, tenantId);
      }
      if (StringUtils.hasText(parentId)) {
        w.eq(CompanyEntity::getParentId, parentId);
      }
      if (StringUtils.hasText(categoryId)) {
        w.eq(CompanyEntity::getCategoryId, categoryId);
      }
      w.eq(CompanyEntity::getStatus, DataStatus.Valid);
      w.orderByOf(orderByClause);
    });

    if (OutputTypes.TREE.equals(output)) {
      List<KeyValueT<String>> categories = entities.stream().map(m ->
        KeyValue.builder().setKey(m.getCategoryId()).setValue(m.getCategoryName())
      ).distinct().toList();
      List<TreeNode> nodeList = new ArrayList<>();
      for (KeyValueT<String> cat : categories) {
        TreeNode node = TreeNode.create().setId(cat.getKey()).setText(cat.getValue()).setType("CAT");
        List<TreeNode> children = entities.stream()
          .filter(x -> cat.getKey().equalsIgnoreCase(x.getCategoryId()))
          .map(m -> TreeNode.create().setId(m.getCompanyId()).setCode(m.getCode()).setText(m.getShortName()).setType("COM"))
          .toList();
        node.setChildren(children);
        nodeList.add(node);
      }
      return HttpResult.okBody(nodeList);
    } else if (OutputTypes.SELECT.equals(output)) {
      List<SelectItem> selectItems = entities.stream().map(m ->
        SelectItem.create().setId(m.getCompanyId()).setCode(m.getCode()).setText(m.getShortName()).setGroup(m.getCategoryName())
      ).toList();
      return HttpResult.okBody(selectItems);
    } else {
      List<CompanyVo> selectItems = entities.stream()
        .map(m -> modelMapper.map(m, CompanyVo.class)).toList();
      return HttpResult.okBody(selectItems);
    }
  }

}
