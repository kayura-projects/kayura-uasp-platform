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
