package org.kayura.uasp.auth.cmd.handler;

import org.kayura.cmd.CommandHandler;
import org.kayura.type.HttpResult;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;
import org.kayura.uasp.auth.cmd.QueryRoleUserCommand;
import org.kayura.uasp.auth.entity.RoleEntity;
import org.kayura.uasp.auth.entity.RoleUserEntity;
import org.kayura.uasp.auth.entity.UserEntity;
import org.kayura.uasp.auth.manage.RoleManager;
import org.kayura.uasp.auth.manage.RoleUserManager;
import org.kayura.uasp.auth.manage.UserManager;
import org.kayura.uasp.role.RoleVo;
import org.kayura.uasp.user.UserVo;
import org.kayura.uasp.utils.OutputTypes;
import org.kayura.utils.CollectionUtils;
import org.kayura.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QueryRoleUserCommandHandler implements CommandHandler<QueryRoleUserCommand, HttpResult> {

  private final RoleUserManager roleUserManager;
  private final RoleManager roleManager;
  private final UserManager userManager;
  private final ModelMapper modelMapper;

  public QueryRoleUserCommandHandler(RoleUserManager roleUserManager,
                                     RoleManager roleManager,
                                     UserManager userManager,
                                     ModelMapper modelMapper) {
    this.roleUserManager = roleUserManager;
    this.roleManager = roleManager;
    this.userManager = userManager;
    this.modelMapper = modelMapper;
  }

  @Transactional(readOnly = true)
  public HttpResult execute(QueryRoleUserCommand command) {

    OutputTypes output = command.getOutput();
    String userId = command.getUserId();
    String roleId = command.getRoleId();

    if (StringUtils.isAllBlank(userId, roleId)) {
      return HttpResult.error("必须指定员工或角色条件。");
    }

    List<RoleUserEntity> entities = roleUserManager.selectList(w -> {
      if (StringUtils.hasText(command.getUserId())) {
        w.eq(RoleUserEntity::getUserId, command.getUserId());
      }
      if (StringUtils.hasText(command.getRoleId())) {
        w.eq(RoleUserEntity::getRoleId, command.getRoleId());
      }
      w.of(command.getQuery());
    });

    if (OutputTypes.ROLE.equals(output)) {
      return this.outputRoles(command, entities);
    } else {
      return this.outputUsers(command, entities);
    }

  }

  private HttpResult outputRoles(QueryRoleUserCommand command, List<RoleUserEntity> entities) {

    PageClause pageClause = command.getPageClause();
    Set<String> roleIds = entities.stream().map(RoleUserEntity::getRoleId).collect(Collectors.toSet());
    if (pageClause != null) {
      PageList<RoleVo> pageList;
      if (CollectionUtils.isNotEmpty(roleIds)) {
        pageList = roleManager.selectPage(w -> {
          w.in(RoleEntity::getRoleId, roleIds);
        }, pageClause).streamMap(m -> modelMapper.map(m, RoleVo.class));
      } else {
        pageList = new PageList<>();
      }
      return HttpResult.okBody(pageList);
    } else {
      List<RoleVo> list;
      if (CollectionUtils.isNotEmpty(roleIds)) {
        list = roleManager.selectList(w -> {
          w.in(RoleEntity::getRoleId, roleIds);
          w.orderByOf(command.getOrderByClause());
        }).stream().map(m -> modelMapper.map(m, RoleVo.class)).toList();
      } else {
        list = new ArrayList<>();
      }
      return HttpResult.okBody(list);
    }
  }

  private HttpResult outputUsers(QueryRoleUserCommand command, List<RoleUserEntity> entities) {

    PageClause pageClause = command.getPageClause();
    Set<String> userIds = entities.stream().map(RoleUserEntity::getUserId).collect(Collectors.toSet());
    if (pageClause != null) {
      PageList<UserVo> pageList;
      if (CollectionUtils.isNotEmpty(userIds)) {
        pageList = userManager.selectPage(w -> {
          w.in(UserEntity::getUserId, userIds);
        }, pageClause).streamMap(m -> modelMapper.map(m, UserVo.class));
      } else {
        pageList = new PageList<>();
      }
      return HttpResult.okBody(pageList);
    } else {
      List<UserVo> list;
      if (CollectionUtils.isNotEmpty(userIds)) {
        list = userManager.selectList(w -> {
          w.in(UserEntity::getUserId, userIds);
          w.orderByOf(command.getOrderByClause());
        }).stream().map(m -> modelMapper.map(m, UserVo.class)).toList();
      } else {
        list = new ArrayList<>();
      }
      return HttpResult.okBody(list);
    }
  }

}
