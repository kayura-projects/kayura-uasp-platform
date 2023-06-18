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
          .collect(Collectors.toList());
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
