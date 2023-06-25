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

package org.kayura.uasp.basic.manage;

import org.kayura.uasp.autono.NewNoArgs;
import org.kayura.uasp.basic.entity.AutoNoConfigEntity;
import org.kayura.uasp.basic.entity.AutoNoCountEntity;
import org.kayura.uasp.basic.model.NewNoSettings;
import org.kayura.uasp.utils.CacheConsts;
import org.kayura.uasp.utils.UaspConsts;
import org.kayura.utils.Assert;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class AutoNoManager implements CacheConsts {

  private final StringRedisTemplate redisTemplate;
  private final ObjectMapper objectMapper;
  private final AutoNoConfigManager configManager;
  private final AutoNoCountManager countManager;

  public AutoNoManager(StringRedisTemplate redisTemplate,
                       ObjectMapper objectMapper,
                       AutoNoConfigManager configManager,
                       AutoNoCountManager countManager) {
    this.redisTemplate = redisTemplate;
    this.objectMapper = objectMapper;
    this.configManager = configManager;
    this.countManager = countManager;
  }

  public String calcNewNo(NewNoArgs args, boolean isPersistent) {

    String lockValue = UUID.randomUUID().toString();
    Boolean lockResult = this.redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, lockValue, Duration.ofSeconds(30));
    if (Boolean.TRUE.equals(lockResult)) {
      try {
        LocalDateTime now = DateUtils.now();
        Map<String, String> params = innerTimeVar(now);
        if (CollectionUtils.isNotEmpty(args.getParams())) {
          params.putAll(args.getParams());
        }

        NewNoSettings settings;
        String json = this.redisTemplate.opsForValue().get(this.makeCacheKey(args));
        if (json == null) {
          settings = this.selectSettingsFromDatabase(args, params);
          this.updateSettingsToRedis(args, settings);
        } else {
          settings = this.readSettingsFormJson(json);
        }

        int newCount = settings.getCountValue() != null
          ? settings.getCountValue() + settings.getIncValue()
          : settings.getIncValue();
        params.put("计数", String.format("%0" + settings.getCountLength() + "d", newCount));

        String newNo = calcCycleValue(settings.getExpression(), params);
        if (isPersistent) {
          settings.setCountValue(newCount);
          this.updateSettingsToDatabase(args, settings);
          this.updateSettingsToRedis(args, settings);
        }
        return newNo;
      } finally {
        String lockValueInRedis = this.redisTemplate.opsForValue().get(LOCK_KEY);
        if (lockValue.equals(lockValueInRedis)) {
          this.redisTemplate.delete(LOCK_KEY);
        }
      }
    } else {
      try {
        Thread.sleep(200L);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
      }
      return calcNewNo(args, isPersistent);
    }
  }

  private void updateSettingsToRedis(NewNoArgs args, NewNoSettings settings) {
    this.redisTemplate.opsForValue().set(this.makeCacheKey(args), this.writeSettingsToJson(settings), Duration.ofSeconds(300));
  }

  private NewNoSettings readSettingsFormJson(String json) {
    try {
      return this.objectMapper.readValue(json, NewNoSettings.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String writeSettingsToJson(NewNoSettings settings) {
    try {
      return this.objectMapper.writeValueAsString(settings);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String makeCacheKey(NewNoArgs args) {

    StringBuilder sb = new StringBuilder("kayura:NO_CACHE_KEY:");
    sb.append(args.getCode()).append("_").append(args.getTenantId());
    Map<String, String> params = args.getParams();
    if (params != null && !params.isEmpty()) {
      sb.append("__");
      for (Map.Entry<String, String> me : params.entrySet()) {
        sb.append(me.getKey()).append("_").append(me.getValue());
      }
    }
    return sb.toString();
  }

  private NewNoSettings selectSettingsFromDatabase(NewNoArgs args, Map<String, String> params) {

    List<AutoNoConfigEntity> configs = configManager.selectList(w -> {
      w.eq(AutoNoConfigEntity::getCode, args.getCode());
      if (UaspConsts.GLOBAL.equalsIgnoreCase(args.getTenantId())) {
        w.isNull(AutoNoConfigEntity::getTenantId);
      } else {
        w.and(y -> y.eq(AutoNoConfigEntity::getTenantId, args.getTenantId()).or().isNull(AutoNoConfigEntity::getTenantId));
      }
    });
    Assert.notEmpty(configs, "指定的自动单据不存在。");

    // 如果查出多条，优先使用本租户下的配置
    final AutoNoConfigEntity config = configs.size() > 1 ?
      configs.stream()
        .filter(x -> StringUtils.hasText(x.getTenantId()))
        .findFirst()
        .orElseThrow(() -> new RuntimeException("缺少本租户下的配置。")) :
      configs.get(0);

    // 计算自定义周期
    String customCycle = calcCycleValue(config.getCustomCycle(), params);
    AutoNoCountEntity noCount = countManager.selectOne(w -> {
      w.select(AutoNoCountEntity::getCountId);
      w.select(AutoNoCountEntity::getCountValue);
      w.eq(AutoNoCountEntity::getDefineId, config.getDefineId());
      w.eq(AutoNoCountEntity::getCycleValue, customCycle);
      if (UaspConsts.GLOBAL.equalsIgnoreCase(args.getTenantId())) {
        w.isNull(AutoNoCountEntity::getTenantId);
      } else {
        w.eq(AutoNoCountEntity::getTenantId, args.getTenantId());
      }
    });

    return NewNoSettings.create()
      .setDefineId(config.getDefineId())
      .setIncValue(config.getIncValue())
      .setCountLength(config.getCountLength())
      .setCountId(noCount != null ? noCount.getCountId() : null)
      .setCountValue(noCount != null ? noCount.getCountValue() : null)
      .setCustomCycle(customCycle)
      .setExpression(config.getExpression());
  }

  private void updateSettingsToDatabase(NewNoArgs args, NewNoSettings settings) {

    if (settings.getCountId() != null) {
      if (countManager.selectCount(x -> x.eq(AutoNoCountEntity::getCountId, settings.getCountId())) > 0) {
        countManager.updateByWhere(w -> {
          w.set(AutoNoCountEntity::getCountValue, settings.getCountValue());
          w.eq(AutoNoCountEntity::getCountId, settings.getCountId());
        });
      } else {
        AutoNoCountEntity countEntity = AutoNoCountEntity.create()
          .setCountId(countManager.nextId())
          .setDefineId(settings.getDefineId())
          .setTenantId(UaspConsts.GLOBAL.equalsIgnoreCase(args.getTenantId()) ? null : args.getTenantId())
          .setCycleValue(settings.getCustomCycle())
          .setCountValue(settings.getCountValue());
        settings.setCountId(countEntity.getCountId());
        countManager.insertOne(countEntity);
      }
    } else {
      AutoNoCountEntity countEntity = AutoNoCountEntity.create()
        .setCountId(countManager.nextId())
        .setDefineId(settings.getDefineId())
        .setTenantId(UaspConsts.GLOBAL.equalsIgnoreCase(args.getTenantId()) ? null : args.getTenantId())
        .setCycleValue(settings.getCustomCycle())
        .setCountValue(1);
      settings.setCountId(countEntity.getCountId());
      countManager.insertOne(countEntity);
    }
  }

  private Map<String, String> innerTimeVar(LocalDateTime now) {
    Map<String, String> timeMap = new HashMap<>();
    timeMap.put("年", String.valueOf(now.getYear()));
    timeMap.put("年:2", String.valueOf(now.getYear()).substring(2));
    timeMap.put("月", String.format("%02d", now.getMonthValue()));
    timeMap.put("日", String.format("%02d", now.getDayOfMonth()));
    timeMap.put("时", String.format("%02d", now.getHour()));
    timeMap.put("分", String.format("%02d", now.getMinute()));
    timeMap.put("秒", String.format("%02d", now.getSecond()));
    return timeMap;
  }

  private String calcCycleValue(String customCycle, Map<String, String> params) {

    String cycleValue = customCycle;
    for (Map.Entry<String, String> m : params.entrySet()) {
      cycleValue = StringUtils.replace(cycleValue, "{" + m.getKey() + "}", m.getValue());
    }
    return cycleValue;
  }
}
