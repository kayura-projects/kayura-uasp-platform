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

package org.kayura.uasp.organize.service;

import org.kayura.except.ExceptUtils;
import org.kayura.security.LoginUser;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.organize.entity.TeamEntity;
import org.kayura.uasp.organize.entity.TeamUserEntity;
import org.kayura.uasp.organize.manage.TeamManager;
import org.kayura.uasp.organize.manage.TeamUserManager;
import org.kayura.uasp.team.*;
import org.kayura.utils.Assert;
import org.kayura.utils.DateUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

  private final ModelMapper modelMapper;
  private final TeamManager teamManager;
  private final TeamUserManager teamUserManager;

  public TeamService(ModelMapper modelMapper, TeamManager teamManager, TeamUserManager teamUserManager) {
    this.modelMapper = modelMapper;
    this.teamManager = teamManager;
    this.teamUserManager = teamUserManager;
  }

  public PageList<TeamVo> queryTeamPage(TeamQuery query, PageClause pageClause) {

    PageList<TeamVo> pageList = teamManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, TeamVo.class));
    return pageList;
  }

  public TeamVo findTeamById(String id) {

    TeamEntity entity = teamManager.selectById(id);
    return entity != null ? modelMapper.map(entity, TeamVo.class) : null;
  }

  public void createTeam(TeamBody form, LoginUser loginUser) {

    Assert.hasText(form.getTenantId(), "必须指定租户。");

    TeamEntity entity = TeamEntity.create();
    BeanUtils.copyProperties(form, entity);
    entity.setTeamId(teamManager.nextId());
    entity.setCreatorId(loginUser.getUserId());
    entity.setCreateTime(DateUtils.now());
    teamManager.insertOne(entity);
  }

  public void updateTeam(String id, TeamBody form, LoginUser loginUser) {

    TeamEntity entity = teamManager.selectById(id);
    Assert.notNull(entity, "更新的对象不存在。");

    if (StringUtils.hasText(form.getCode()) && !form.getCode().equalsIgnoreCase(entity.getCode()) && teamManager.selectCount(w -> {
      w.eq(TeamEntity::getTenantId, loginUser.getTenantId());
      w.eq(TeamEntity::getCode, form.getCode());
    }) > 0) {
      ExceptUtils.business("编号已经被占用。");
    }

    teamManager.updateByWhere(w -> {
      w.set(TeamEntity::getCode, form.getCode());
      w.set(TeamEntity::getName, form.getName());
      w.set(TeamEntity::getSort, form.getSort());
      w.set(TeamEntity::getStartTime, form.getStartTime());
      w.set(TeamEntity::getEndTime, form.getEndTime());
      w.set(TeamEntity::getStatus, form.getStatus());
      w.set(TeamEntity::getRemark, form.getRemark());
      w.set(TeamEntity::getUpdaterId, loginUser.getUserId());
      w.set(TeamEntity::getUpdateTime, DateUtils.now());
      w.eq(TeamEntity::getTeamId, id);
    });
  }

  public void deleteTeam(String id) {
    teamManager.deleteById(id);
  }

  public PageList<TeamUserVo> queryTeamUserPage(TeamUserQuery query, PageClause pageClause) {

    PageList<TeamUserVo> pageList = teamUserManager.selectPage(w -> {
      w.of(query);
    }, pageClause).streamMap(m -> modelMapper.map(m, TeamUserVo.class));
    return pageList;
  }

  public TeamUserVo findTeamUserById(String id) {

    TeamUserEntity entity = teamUserManager.selectById(id);
    return entity != null ? modelMapper.map(entity, TeamUserVo.class) : null;
  }

  public void createTeamUser(TeamUserBody form) {

    Assert.hasText(form.getTeamId(), "必须指定群组。");
    Assert.hasText(form.getEmployeeId(), "必须指定用户。");

    TeamUserEntity entity = TeamUserEntity.create();
    BeanUtils.copyProperties(form, entity);
    entity.setMemberId(teamUserManager.nextId());
    teamUserManager.insertOne(entity);
  }

  public void updateTeamUser(String id, TeamUserBody form) {

    TeamUserEntity entity = teamUserManager.selectById(id);
    Assert.notNull(entity, "更新的对象不存在。");

    teamUserManager.updateByWhere(w -> {
      w.set(TeamUserEntity::getDuty, form.getDuty());
      w.set(TeamUserEntity::getLevel, form.getLevel());
      w.set(TeamUserEntity::getJoinTime, form.getJoinTime());
      w.set(TeamUserEntity::getLeaveTime, form.getLeaveTime());
      w.set(TeamUserEntity::getRemark, form.getRemark());
      w.eq(TeamUserEntity::getMemberId, id);
    });
  }

  public void deleteTeamUser(String id) {
    teamUserManager.deleteById(id);
  }
}
