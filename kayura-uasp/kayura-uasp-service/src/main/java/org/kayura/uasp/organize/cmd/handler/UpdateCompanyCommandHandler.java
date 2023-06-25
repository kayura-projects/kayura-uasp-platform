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
import org.kayura.type.HttpResult;
import org.kayura.uasp.company.CompanyPayload;
import org.kayura.uasp.organize.cmd.UpdateCompanyCommand;
import org.kayura.uasp.organize.entity.CompanyEntity;
import org.kayura.uasp.organize.entity.CompanyLeaderEntity;
import org.kayura.uasp.organize.manage.CompanyLeaderManager;
import org.kayura.uasp.organize.manage.CompanyManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UpdateCompanyCommandHandler implements CommandHandler<UpdateCompanyCommand, HttpResult> {

  private final CompanyManager companyManager;
  private final CompanyLeaderManager leaderManager;

  public UpdateCompanyCommandHandler(CompanyManager companyManager, CompanyLeaderManager leaderManager) {
    this.companyManager = companyManager;
    this.leaderManager = leaderManager;
  }

  @Transactional
  public HttpResult execute(UpdateCompanyCommand command) {

    LoginUser loginUser = command.getLoginUser();
    CompanyPayload payload = command.getPayload();
    String companyId = Optional.ofNullable(payload.getCompanyId()).orElse(payload.getCompanyId());

    CompanyEntity entity = companyManager.selectById(companyId);
    if (entity == null) {
      return HttpResult.error("更新的公司不存在。");
    }

    if (StringUtils.hasText(payload.getCode()) && !Objects.equals(payload.getCode(), entity.getCode()) &&
      companyManager.selectCount(w -> {
        w.eq(CompanyEntity::getCode, payload.getCode());
        w.notEq(CompanyEntity::getCompanyId, companyId);
      }) > 0) {
      return HttpResult.error("模块编码已经被占用。");
    }

    entity.setCategoryId(payload.getCategoryId());
    entity.setParentId(payload.getParentId());
    entity.setCode(payload.getCode());
    entity.setShortName(payload.getShortName());
    entity.setFullName(payload.getFullName());
    entity.setAddress(payload.getAddress());
    entity.setPostCode(payload.getPostCode());
    entity.setContract(payload.getContract());
    entity.setMobile(payload.getMobile());
    entity.setTel(payload.getTel());
    entity.setFax(payload.getFax());
    entity.setEmail(payload.getEmail());
    entity.setSort(payload.getSort());
    entity.setLocation(payload.getLocation());
    entity.setStatus(payload.getStatus());
    entity.setRemark(payload.getRemark());
    entity.setUpdaterId(loginUser.getUserId());
    entity.setUpdateTime(DateUtils.now());
    companyManager.updateById(companyId, entity);

    // 添加领导数据，简单处理
    leaderManager.deleteByWhere(w -> w.eq(CompanyLeaderEntity::getCompanyId, companyId));
    if (CollectionUtils.isNotEmpty(payload.getLeaders())) {
      List<CompanyLeaderEntity> collect = payload.getLeaders().stream().map(m ->
        CompanyLeaderEntity.create()
          .setCompanyId(entity.getCompanyId())
          .setLeaderId(m.getLeaderId())
          .setDuty(m.getDuty())
      ).collect(Collectors.toList());
      leaderManager.insertBatch(collect);
    }

    return HttpResult.ok();
  }

}
