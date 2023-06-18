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

package org.kayura.uasp.workflow.manage;

import org.kayura.mybatis.manager.impl.CrudManagerImpl;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.uasp.workflow.UsedOpinionVo;
import org.kayura.uasp.workflow.entity.UsedOpinionEntity;
import org.kayura.uasp.workflow.mapper.UsedOpinionMapper;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OpinionManager extends CrudManagerImpl<UsedOpinionMapper, UsedOpinionEntity> {

  private final ModelMapper modelMapper;

  protected OpinionManager(UsedOpinionMapper baseMapper,
                           ModelMapper modelMapper) {
    super(baseMapper);
    this.modelMapper = modelMapper;
  }

  public List<UsedOpinionVo> selectTopList(String userId, int top) {

    PageClause pageClause = PageClause.of(1, top)
      .setOrderby(OrderByClause.of("useCount", OrderByClause.DESC));
    List<UsedOpinionVo> list = this.selectPage(w -> {
        w.select(UsedOpinionEntity::getOpinionId);
        w.select(UsedOpinionEntity::getContent);
        w.eq(UsedOpinionEntity::getUserId, userId);
      }, pageClause)
      .getRows().stream().map(m -> modelMapper.map(m, UsedOpinionVo.class)).toList();
    return list;
  }

  public void insertOrUpdate(String userId, String content) {

    if (StringUtils.hasText(content)) {
      UsedOpinionEntity entity = this.selectOne(w -> {
        w.eq(UsedOpinionEntity::getUserId, userId);
        w.eq(UsedOpinionEntity::getContent, content);
      });
      if (entity == null) {
        entity = UsedOpinionEntity.create()
          .setUserId(userId)
          .setContent(content)
          .setOpinionId(this.nextId())
          .setUseCount(0);
        this.insertOne(entity);
      } else {
        Integer newCount = Optional
          .ofNullable(entity.getUseCount())
          .orElse(0) + 1;
        UsedOpinionEntity finalEntity = entity;
        this.updateByWhere(w -> {
          w.set(UsedOpinionEntity::getUseCount, newCount);
          w.eq(UsedOpinionEntity::getOpinionId, finalEntity.getOpinionId());
        });
      }
    }
  }

}
