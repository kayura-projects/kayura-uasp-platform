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
