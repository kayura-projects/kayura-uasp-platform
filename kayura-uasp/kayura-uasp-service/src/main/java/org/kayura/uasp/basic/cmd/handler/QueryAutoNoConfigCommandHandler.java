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

package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.autono.AutoNoQuery;
import org.kayura.uasp.autono.AutoNoVo;
import org.kayura.uasp.basic.cmd.QueryAutoNoConfigCommand;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.manage.AutoNoConfigManager;
import org.kayura.uasp.utils.UaspConsts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class QueryAutoNoConfigCommandHandler implements CommandHandler<QueryAutoNoConfigCommand, HttpResult> {

  private final AutoNoConfigManager configManager;
  private final ModelMapper modelMapper;

  public QueryAutoNoConfigCommandHandler(AutoNoConfigManager configManager,
                                         ModelMapper modelMapper) {
    this.configManager = configManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryAutoNoConfigCommand command) {

    LoginUser loginUser = command.getLoginUser();
    AutoNoQuery query = command.getQuery();
    PageClause pageClause = command.getPageClause();

    if (loginUser.hasTenantUser()) {
      query.setAppId(loginUser.getAppId());
      query.setTenantId(loginUser.getTenantId());
    }

    // 先拿全局配置
    PageList<AutoNoConfigEntity> globalConfigs = configManager.selectPage(w -> {
      w.eq(AutoNoConfigEntity::getAppId, query.getAppId());
      w.isNull(AutoNoConfigEntity::getTenantId);
    }, pageClause);

    // 再拿租户配置
    PageList<AutoNoVo> pageList = globalConfigs.streamMap(m -> modelMapper.map(m, AutoNoVo.class));
    List<AutoNoVo> rows = pageList.getRows();
    if (!rows.isEmpty()) {

      List<String> defineIds = rows.stream().map(AutoNoVo::getDefineId).toList();
      List<AutoNoConfigEntity> configs = configManager.selectList(w -> {
        w.in(AutoNoConfigEntity::getDefineId, defineIds);
        if (UaspConsts.GLOBAL.equalsIgnoreCase(query.getTenantId())) {
          w.isNull(AutoNoConfigEntity::getTenantId);
        } else {
          w.eq(AutoNoConfigEntity::getTenantId, query.getTenantId());
        }
      });

      for (AutoNoVo row : rows) {
        Optional<AutoNoConfigEntity> first = configs.stream().filter(x -> row.getDefineId().equals(x.getDefineId())).findFirst();
        if (first.isPresent()) {
          BeanUtils.copyProperties(first.get(), row);
          row.setHasCustom(Boolean.TRUE);
        } else {
          row.setCreatorId(null);
          row.setCreateTime(null);
          row.setUpdaterId(null);
          row.setUpdateTime(null);
          row.setRemark(null);
        }
      }
    }
    return HttpResult.okBody(pageList);
  }

}
