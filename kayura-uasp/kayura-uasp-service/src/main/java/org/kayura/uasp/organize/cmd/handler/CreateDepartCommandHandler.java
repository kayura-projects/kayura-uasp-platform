package org.kayura.uasp.organize.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.uasp.organize.DepartPayload;
import org.kayura.uasp.organize.DepartVo;
import org.kayura.uasp.organize.cmd.CreateDepartCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.DepartEntity;
import org.kayura.uasp.organize.entity.DepartLeaderEntity;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.uasp.organize.manage.DepartLeaderManager;
import org.kayura.uasp.organize.manage.DepartManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CreateDepartCommandHandler implements CommandHandler<CreateDepartCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final DepartManager departManager;
  private final DepartLeaderManager departLeaderManager;
  private final ModelMapper modelMapper;

  public CreateDepartCommandHandler(CompanyManager companyManager,
                                    DepartManager departManager,
                                    DepartLeaderManager departLeaderManager,
                                    ModelMapper modelMapper) {
    this.companyManager = companyManager;
    this.departManager = departManager;
    this.departLeaderManager = departLeaderManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateDepartCommand command) {

    LoginUser loginUser = command.getLoginUser();
    DepartPayload payload = command.getPayload();

    if (StringUtils.isAllBlank(payload.getParentId(), payload.getCompanyId())) {
      return HttpResult.error("必需指定公司ID或上级部门ID。");
    }

    if (StringUtils.hasText(payload.getCompanyId())) {
      if (companyManager.selectCount(w -> w.eq(CompanyEntity::getCompanyId, payload.getCompanyId())) == 0) {
        return HttpResult.error("指定的公司ID不存在。");
      }
    } else {
      DepartEntity depart = departManager.selectById(payload.getParentId());
      if (depart == null) {
        return HttpResult.error("指定的上级部门不存在。");
      }
      payload.setCompanyId(depart.getCompanyId());
    }

    if (StringUtils.hasText(payload.getCode()) && departManager.selectCount(w -> {
      w.eq(DepartEntity::getCompanyId, payload.getCompanyId());
      w.eq(DepartEntity::getCode, payload.getCode());
    }) > 0) {
      return HttpResult.error("编号已经存在，不允许重复。");
    }

    DepartEntity entity = DepartEntity.create();
    entity.setDepartId(departManager.nextId());
    entity.setCompanyId(payload.getCompanyId());
    entity.setParentId(payload.getParentId());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setRemark(payload.getRemark());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    entity.setSort(payload.getSort());
    entity.setStatus(payload.getStatus());
    departManager.insertOne(entity);

    // 添加领导数据
    if (CollectionUtils.isNotEmpty(payload.getLeaders())) {
      List<DepartLeaderEntity> collect = payload.getLeaders().stream().map(m ->
        DepartLeaderEntity.create()
          .setDepartId(entity.getDepartId())
          .setLeaderId(m.getLeaderId())
          .setDuty(m.getDuty())
      ).collect(Collectors.toList());
      departLeaderManager.insertBatch(collect);
    }

    DepartVo model = modelMapper.map(entity, DepartVo.class);
    return HttpResult.okBody(model);
  }
}
