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
      }).stream().map(m -> modelMapper.map(m, LeaderVo.class)).collect(Collectors.toList());
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
      }).stream().map(m -> modelMapper.map(m, LeaderVo.class)).collect(Collectors.toList());
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
