package org.kayura.uasp.basic.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.security.LoginUser;
import org.kayura.type.DataStatus;
import org.kayura.type.HttpResult;
import org.kayura.type.SelectItem;
import org.kayura.uasp.basic.cmd.ChooseDictItemCommand;
import org.kayura.uasp.basic.entity.DictItemEntity;
import org.kayura.uasp.basic.manage.DictItemManager;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ChooseDictItemCommandHandler implements CommandHandler<ChooseDictItemCommand, HttpResult> {

  private final DictItemManager itemManager;

  public ChooseDictItemCommandHandler(DictItemManager itemManager) {
    this.itemManager = itemManager;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(ChooseDictItemCommand command) {

    LoginUser loginUser = command.getLoginUser();
    String tenantId = command.getTenantId();
    Map<String, String> defines = command.getDefines();

    if (CollectionUtils.isNotEmpty(defines)) {
      Map<String, List<SelectItem>> result = new HashMap<>();
      for (Map.Entry<String, String> map : defines.entrySet()) {
        result.put(map.getValue(), this.chooseDictItems(loginUser, map.getKey(), tenantId));
      }
      return HttpResult.okBody(result);
    } else {
      String defineCode = command.getDefine();
      List<SelectItem> selectItems = chooseDictItems(loginUser, defineCode, tenantId);
      return HttpResult.okBody(selectItems);
    }
  }

  @NotNull
  private List<SelectItem> chooseDictItems(LoginUser loginUser, String defineCode, String tenantId) {

    List<DictItemEntity> entities = itemManager.selectList(w -> {
      w.select(DictItemEntity::getItemId);
      w.select(DictItemEntity::getCode);
      w.select(DictItemEntity::getName);
      // 普通用户拿自己的租户ID，后台管理拿指定的或为空的。
      if (StringUtils.hasText(loginUser.getTenantId())) {
        w.and(x -> x.eq(DictItemEntity::getTenantId, loginUser.getTenantId()).or(y -> y.isNull(DictItemEntity::getTenantId)));
      } else if (StringUtils.hasText(tenantId)) {
        w.and(x -> x.eq(DictItemEntity::getTenantId, tenantId).or(y -> y.isNull(DictItemEntity::getTenantId)));
      } else {
        w.isNull(DictItemEntity::getTenantId);
      }
      w.eq(DictItemEntity::getDefineCode, defineCode);
      w.eq(DictItemEntity::getStatus, DataStatus.Valid);
    });

    List<SelectItem> selectItems = entities.stream().map(m ->
      SelectItem.create().setId(m.getItemId()).setCode(m.getCode()).setText(m.getName())
    ).collect(Collectors.toList());

    return selectItems;
  }
}
