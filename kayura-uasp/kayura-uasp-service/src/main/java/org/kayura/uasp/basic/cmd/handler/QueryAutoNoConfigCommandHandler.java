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
import org.kayura.uasp.utils.UaspConstants;
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

      List<String> defineIds = rows.stream().map(AutoNoVo::getDefineId).collect(Collectors.toList());
      List<AutoNoConfigEntity> configs = configManager.selectList(w -> {
        w.in(AutoNoConfigEntity::getDefineId, defineIds);
        if (UaspConstants.GLOBAL.equalsIgnoreCase(query.getTenantId())) {
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
