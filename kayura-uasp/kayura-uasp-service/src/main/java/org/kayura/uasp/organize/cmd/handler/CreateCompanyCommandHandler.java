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
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.uasp.auth.entity.TenantEntity;
import org.kayura.uasp.auth.manage.TenantManager;
import org.kayura.uasp.company.CompanyPayload;
import org.kayura.uasp.company.CompanyTypes;
import org.kayura.uasp.company.CompanyVo;
import org.kayura.uasp.organize.cmd.CreateCompanyCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.CompanyLeaderEntity;
import org.kayura.uasp.organize.manage.CompanyLeaderManager;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CreateCompanyCommandHandler implements CommandHandler<CreateCompanyCommand, HttpResult> {

  private final TenantManager tenantManager;
  private final CompanyManager companyManager;
  private final CompanyLeaderManager leaderManager;
  private final ModelMapper modelMapper;

  public CreateCompanyCommandHandler(TenantManager tenantManager,
                                     CompanyManager companyManager,
                                     CompanyLeaderManager leaderManager,
                                     ModelMapper modelMapper) {
    this.tenantManager = tenantManager;
    this.companyManager = companyManager;
    this.leaderManager = leaderManager;
    this.modelMapper = modelMapper;
  }

  @Transactional
  public HttpResult execute(CreateCompanyCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyPayload payload = command.getPayload();

    if (StringUtils.hasText(loginUser.getTenantId())) {
      payload.setTenantId(loginUser.getTenantId());
    }

    if (CompanyTypes.Child.equals(payload.getType()) && StringUtils.isBlank(payload.getParentId())) {
      return HttpResult.error("必需指定子公司的上级公司。");
    }

    if (tenantManager.selectCount(w -> {
      w.eq(TenantEntity::getTenantId, payload.getTenantId());
    }) == 0) {
      return HttpResult.error("指定的租户ID不存在。");
    }

    if (companyManager.selectCount(w -> {
      w.eq(CompanyEntity::getTenantId, payload.getTenantId());
      w.eq(CompanyEntity::getCode, payload.getCode());
    }) > 0) {
      return HttpResult.error("编号已经被占用。");
    }

    if (companyManager.selectCount(w -> {
      w.eq(CompanyEntity::getTenantId, payload.getTenantId());
      w.eq(CompanyEntity::getShortName, payload.getShortName());
    }) > 0) {
      return HttpResult.error("简称已经被占用。");
    }

    CompanyEntity entity = CompanyEntity.create()
      .setCompanyId(companyManager.nextId())
      .setTenantId(payload.getTenantId())
      .setParentId(payload.getParentId())
      .setType(payload.getType())
      .setCategoryId(payload.getCategoryId())
      .setCode(payload.getCode())
      .setShortName(payload.getShortName())
      .setFullName(Optional.ofNullable(payload.getFullName()).orElse(payload.getShortName()))
      .setAddress(payload.getAddress())
      .setPostCode(payload.getPostCode())
      .setContract(payload.getContract())
      .setMobile(payload.getMobile())
      .setTel(payload.getTel())
      .setFax(payload.getFax())
      .setEmail(payload.getEmail())
      .setSort(payload.getSort())
      .setLocation(payload.getLocation())
      .setCreatorId(loginUser.getUserId())
      .setCreateTime(DateUtils.now())
      .setStatus(Optional.ofNullable(payload.getStatus()).orElse(DataStatus.Draft))
      .setRemark(payload.getRemark());
    companyManager.insertOne(entity);

    // 添加领导数据
    if (CollectionUtils.isNotEmpty(payload.getLeaders())) {
      List<CompanyLeaderEntity> collect = payload.getLeaders().stream().map(m ->
        CompanyLeaderEntity.create()
          .setCompanyId(entity.getCompanyId())
          .setLeaderId(m.getLeaderId())
          .setDuty(m.getDuty())
      ).collect(Collectors.toList());
      leaderManager.insertBatch(collect);
    }

    CompanyVo model = modelMapper.map(entity, CompanyVo.class);
    return HttpResult.okBody(model);
  }

}
