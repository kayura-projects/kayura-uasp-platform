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
import org.kayura.type.HttpResult;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.organize.DepartVo;
import org.kayura.uasp.organize.LeaderVo;
import org.kayura.uasp.organize.OrganizeTypes;
import org.kayura.uasp.organize.PositionVo;
import org.kayura.uasp.organize.cmd.GetOrganizeCommand;
import org.kayura.uasp.organize.entity.*;
import org.kayura.uasp.organize.manage.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetOrganizeCommandHandler implements CommandHandler<GetOrganizeCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final CompanyLeaderManager companyLeaderManager;
  private final DepartManager departManager;
  private final DepartLeaderManager departLeaderManager;
  private final PositionManager positionManager;
  private final ModelMapper modelMapper;

  public GetOrganizeCommandHandler(CompanyManager companyManager,
                                   CompanyLeaderManager companyLeaderManager,
                                   DepartManager departManager,
                                   DepartLeaderManager departLeaderManager,
                                   PositionManager positionManager,
                                   ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.companyLeaderManager = companyLeaderManager;
    this.departManager = departManager;
    this.departLeaderManager = departLeaderManager;
    this.positionManager = positionManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetOrganizeCommand command) {

    OrganizeTypes orgType = command.getOrgType();
    String orgId = command.getOrgId();

    Object model = null;
    if (OrganizeTypes.Company.equals(orgType)) {
      model = this.findCompanyById(orgId);
    } else if (OrganizeTypes.Depart.equals(orgType)) {
      model = this.findDepartById(orgId);
    } else if (OrganizeTypes.Position.equals(orgType)) {
      model = this.findPositionById(orgId);
    }
    return HttpResult.okBody(model);
  }

  protected CompanyVo findCompanyById(String id) {

    CompanyVo model = null;
    CompanyEntity entity = companyManager.selectById(id);
    if (entity != null) {
      model = modelMapper.map(entity, CompanyVo.class);
      List<LeaderVo> leaders = companyLeaderManager.selectList(w -> {
        w.select(CompanyLeaderEntity::getLeaderId);
        w.select(CompanyLeaderEntity::getLeaderName);
        w.select(CompanyLeaderEntity::getDuty);
        w.eq(CompanyLeaderEntity::getCompanyId, id);
      }).stream().map(m -> modelMapper.map(m, LeaderVo.class)).toList();
      model.setLeaders(leaders);
    }
    return model;
  }

  protected DepartVo findDepartById(String id) {

    DepartVo model = null;
    DepartEntity entity = departManager.selectById(id);
    if (entity != null) {
      model = modelMapper.map(entity, DepartVo.class);
      List<LeaderVo> leaders = departLeaderManager.selectList(w -> {
        w.select(DepartLeaderEntity::getLeaderId);
        w.select(DepartLeaderEntity::getLeaderName);
        w.select(DepartLeaderEntity::getDuty);
        w.eq(DepartLeaderEntity::getDepartId, id);
      }).stream().map(m -> modelMapper.map(m, LeaderVo.class)).toList();
      model.setLeaders(leaders);
    }
    return model;
  }

  protected PositionVo findPositionById(String id) {

    PositionVo model = null;
    PositionEntity entity = positionManager.selectById(id);
    if (entity != null) {
      model = modelMapper.map(entity, PositionVo.class);
    }
    return model;
  }

}
