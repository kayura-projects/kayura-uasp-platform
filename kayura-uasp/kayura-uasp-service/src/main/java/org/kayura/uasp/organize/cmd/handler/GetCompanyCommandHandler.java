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
import org.kayura.uasp.organize.LeaderVo;
import org.kayura.uasp.organize.cmd.GetCompanyCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.CompanyLeaderEntity;
import org.kayura.uasp.organize.manage.CompanyLeaderManager;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetCompanyCommandHandler implements CommandHandler<GetCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final CompanyLeaderManager leaderManager;
  private final ModelMapper modelMapper;

  public GetCompanyCommandHandler(CompanyManager companyManager,
                                  CompanyLeaderManager leaderManager,
                                  ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.leaderManager = leaderManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(GetCompanyCommand command) {

    String companyId = command.getCompanyId();

    CompanyEntity entity = companyManager.selectById(companyId);
    if (entity == null) {
      return HttpResult.error("获取的记录不存在。");
    }

    // Leader
    List<LeaderVo> leaders = leaderManager.selectList(w -> {
      w.select(CompanyLeaderEntity::getLeaderId);
      w.select(CompanyLeaderEntity::getLeaderName);
      w.select(CompanyLeaderEntity::getDuty);
      w.eq(CompanyLeaderEntity::getCompanyId, companyId);
    }).stream().map(m -> modelMapper.map(m, LeaderVo.class)).collect(Collectors.toList());

    CompanyVo model = modelMapper.map(entity, CompanyVo.class);
    model.setLeaders(leaders);
    return HttpResult.okBody(model);
  }

}
