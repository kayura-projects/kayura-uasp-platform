package org.kayura.skeleton.service;

import org.kayura.security.LoginUser;
import org.kayura.skeleton.BlankPayload;
import org.kayura.skeleton.BlankQuery;
import org.kayura.skeleton.BlankVo;
import org.kayura.skeleton.entity.BlankEntity;
import org.kayura.skeleton.manage.BlankManager;
import org.kayura.type.OrderByClause;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.common.IdPayload;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BlankService {

  private final BlankManager blankManager;
  private final ModelMapper modelMapper;

  public BlankService(BlankManager blankManager, ModelMapper modelMapper) {
    this.blankManager = blankManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public PageList<BlankVo> selectBlankPage(BlankQuery query, PageClause pageClause) {

    PageList<BlankVo> pageList = blankManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, BlankVo.class));
    return pageList;
  }

  @Transactional(readOnly = true)
  public List<BlankVo> selectBlankList(BlankQuery query, OrderByClause orderByClause) {

    List<BlankVo> list = blankManager.selectList(w -> {
      w.of(query);
      w.orderByOf(orderByClause);
    }).stream().map(m -> modelMapper.map(m, BlankVo.class)).toList();
    return list;
  }

  @Transactional(readOnly = true)
  public BlankVo selectById(String id) {

    BlankVo model = null;
    BlankEntity entity = blankManager.selectById(id);
    if (entity != null) {
      model = modelMapper.map(entity, BlankVo.class);
    }
    return model;
  }

  @Transactional
  public void createBlank(BlankPayload payload, LoginUser loginUser) {

    BlankEntity entity = new BlankEntity();
    entity.setId(blankManager.nextId());
    entity.setCode(payload.getCode());
    entity.setName(payload.getName());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(LocalDateTime.now());
    blankManager.insertOne(entity);
  }

  @Transactional
  public void updateBlank(String id, BlankPayload payload, LoginUser loginUser) {

    BlankEntity entity = blankManager.selectById(id);
    if (entity != null) {
      entity.setCode(payload.getCode());
      entity.setName(payload.getName());
      entity.setUpdaterId(loginUser.getUserId());
      entity.setUpdateTime(LocalDateTime.now());
      blankManager.updateById(id, entity);
    }
  }

  @Transactional
  public void deleteBlank(IdPayload payload) {

    if (CollectionUtils.isNotEmpty(payload.getIds())) {
      blankManager.deleteByIds(payload.getIds());
    } else if (StringUtils.isNotBlank(payload.getId())) {
      blankManager.deleteById(payload.getId());
    }
  }
}
