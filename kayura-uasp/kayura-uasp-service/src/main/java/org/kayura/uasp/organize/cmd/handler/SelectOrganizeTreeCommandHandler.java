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
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.TreeNode;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.cmd.SelectOrganizeTreeCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.PositionEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.uasp.organize.manage.PositionManager;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SelectOrganizeTreeCommandHandler implements CommandHandler<SelectOrganizeTreeCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final PositionManager positionManager;

  public SelectOrganizeTreeCommandHandler(CompanyManager companyManager,
                                          DepartManager departManager,
                                          PositionManager positionManager) {
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.positionManager = positionManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(SelectOrganizeTreeCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String companyId = command.getCompanyId();
    DataStatus status = command.getStatus();
    int level = Optional.ofNullable(command.getLevel()).orElse(3);
    if (level < 1 || level > 3) {
      return HttpResult.error("level 参数必需价于1~3。");
    }

    if (loginUser.hasTenantUser()) {
      companyId = loginUser.getCompanyId();
    }

    // 返回结果集
    List<TreeNode> treeNodes = new ArrayList<>();

    // 我的公司
    final String finalCompanyId = companyId;
    List<CompanyEntity> thisCompanies = companyManager.selectList(w -> {
      w.select(CompanyEntity::getCompanyId);
      w.select(CompanyEntity::getParentId);
      w.select(CompanyEntity::getShortName);
      if (StringUtils.hasText(finalCompanyId)) {
        w.eq(CompanyEntity::getCompanyId, finalCompanyId);
      } else {
        w.in(CompanyEntity::getType, CompanyTypes.Tenant);
      }
      if (status != null) {
        w.eq(CompanyEntity::getStatus, status);
      }
    });
    for (CompanyEntity thisCompany : thisCompanies) {

      List<CompanyEntity> allCompany = new ArrayList<>();
      List<DepartEntity> allDepart = new ArrayList<>();
      List<PositionEntity> allPosition = new ArrayList<>();

      // 查找所有子公司
      allCompany.add(thisCompany);
      findChildCompany(thisCompany, allCompany);

      // 加载指定公司的所有部门及岗位
      findDepartAndPosition(allCompany, level, allDepart, allPosition);

      // 构建组织架构树
      TreeNode treeNode = TreeNode.create()
        .setId(thisCompany.getCompanyId())
        .setText(thisCompany.getShortName())
        .setType(OrganizeTypes.Company.getValue());
      treeNodes.add(treeNode);
      buildCompanyTree(treeNode, allCompany, allDepart, allPosition);
    }

    return HttpResult.okBody(treeNodes);
  }

  private void buildCompanyTree(TreeNode treeNode,
                                List<CompanyEntity> allCompany,
                                List<DepartEntity> allDepart,
                                List<PositionEntity> allPosition) {

    if (!allDepart.isEmpty()) {
      List<DepartEntity> departList = allDepart.stream()
        .filter(x -> StringUtils.equals(x.getCompanyId(), treeNode.getId()) && StringUtils.isBlank(x.getParentId()))
        .toList();
      for (DepartEntity dd : departList) {
        TreeNode node = TreeNode.create()
          .setId(dd.getDepartId())
          .setText(dd.getName())
          .setType(OrganizeTypes.Depart.getValue());
        treeNode.addChildren(node);
        this.buildDepartTree(node, allDepart, allPosition);
      }
    }

    List<CompanyEntity> collect = allCompany.stream()
      .filter(x -> treeNode.getId().equals(x.getParentId()))
      .toList();
    for (CompanyEntity cc : collect) {
      TreeNode node = TreeNode.create()
        .setId(cc.getCompanyId())
        .setText(cc.getShortName())
        .setType(OrganizeTypes.Company.getValue());
      treeNode.addChildren(node);
      buildCompanyTree(node, allCompany, allDepart, allPosition);
    }
  }

  private void buildDepartTree(TreeNode treeNode,
                               List<DepartEntity> allDepart,
                               List<PositionEntity> allPosition) {

    List<DepartEntity> departs = allDepart.stream()
      .filter(x -> treeNode.getId().equals(x.getParentId()))
      .toList();
    for (DepartEntity dd : departs) {
      TreeNode node = TreeNode.create()
        .setId(dd.getDepartId())
        .setText(dd.getName())
        .setType(OrganizeTypes.Depart.getValue());
      treeNode.addChildren(node);
      buildDepartTree(node, allDepart, allPosition);
    }

    if (!allPosition.isEmpty()) {
      List<PositionEntity> positions = allPosition.stream()
        .filter(x -> treeNode.getId().equals(x.getDepartId()))
        .toList();
      for (PositionEntity pp : positions) {
        TreeNode node = TreeNode.create()
          .setId(pp.getPositionId())
          .setText(pp.getName())
          .setType(OrganizeTypes.Position.getValue());
        treeNode.addChildren(node);
      }
    }
  }

  private void findChildCompany(CompanyEntity thisCompany, List<CompanyEntity> companyList) {

    List<CompanyEntity> collect = companyManager.selectList(w -> {
      w.select(CompanyEntity::getCompanyId);
      w.select(CompanyEntity::getParentId);
      w.select(CompanyEntity::getShortName);
      w.eq(CompanyEntity::getParentId, thisCompany.getCompanyId());
      w.eq(CompanyEntity::getStatus, DataStatus.Valid);
    });
    for (CompanyEntity cc : collect) {
      companyList.add(cc);
      findChildCompany(cc, companyList);
    }
  }

  private void findDepartAndPosition(List<CompanyEntity> allCompany,
                                     int level,
                                     List<DepartEntity> allDepart,
                                     List<PositionEntity> allPosition) {

    if (level > 1) {
      List<String> companyIds = allCompany.stream()
        .map(CompanyEntity::getCompanyId)
        .distinct()
        .collect(Collectors.toList());
      if (!companyIds.isEmpty()) {
        List<DepartEntity> collect = departManager.selectList(w -> {
          w.select(DepartEntity::getDepartId);
          w.select(DepartEntity::getParentId);
          w.select(DepartEntity::getCompanyId);
          w.select(DepartEntity::getName);
          w.in(DepartEntity::getCompanyId, companyIds);
          w.eq(DepartEntity::getStatus, DataStatus.Valid);
        });
        if (!collect.isEmpty()) {
          allDepart.addAll(collect);
        }
      }
      // 如果类型为岗位
      if (level > 2 && !allDepart.isEmpty()) {
        List<String> departIds = allDepart.stream()
          .map(DepartEntity::getDepartId)
          .collect(Collectors.toList());
        if (!departIds.isEmpty()) {
          List<PositionEntity> collect = positionManager.selectList(w -> {
            w.select(PositionEntity::getPositionId);
            w.select(PositionEntity::getDepartId);
            w.select(PositionEntity::getName);
            w.in(PositionEntity::getDepartId, departIds);
            w.eq(PositionEntity::getStatus, DataStatus.Valid);
          });
          if (!collect.isEmpty()) {
            allPosition.addAll(collect);
          }
        }
      }
    }
  }
}
