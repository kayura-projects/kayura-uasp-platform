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

package org.kayura.uasp.auth.entity;

import org.kayura.mybatis.annotation.mapper.*;
import org.kayura.type.StringList;
import org.kayura.uasp.dev.entity.ModuleDefineEntity;
import org.kayura.uasp.privilege.PrivilegeTypes;

/**
 * 功能权限表(uasp_privilege) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_privilege")
public class PrivilegeEntity {

  /** 关联ID:租户ID,角色ID,用户ID,公司ID; */
  private String linkId;
  /** 权限类型:T租户,R角色,U用户,C往来公司 */
  private PrivilegeTypes type;
  /** 模块ID */
  @ForeignKey(entity = ModuleDefineEntity.class, alias = "mm")
  private String moduleId;
  @RefColumn(from = "mm", value = "code_")
  private String moduleCode;
  @RefColumn(from = "mm", value = "name_")
  private String moduleName;
  @RefColumn(from = "mm", value = "app_id_")
  private String appId;
  /** 动作授权 */
  private StringList actions;

  public static PrivilegeEntity create() {
    return new PrivilegeEntity();
  }

  public String getLinkId() {
    return linkId;
  }

  public PrivilegeEntity setLinkId(String linkId) {
    this.linkId = linkId;
    return this;
  }

  public PrivilegeTypes getType() {
    return type;
  }

  public PrivilegeEntity setType(PrivilegeTypes type) {
    this.type = type;
    return this;
  }

  public String getModuleId() {
    return moduleId;
  }

  public PrivilegeEntity setModuleId(String moduleId) {
    this.moduleId = moduleId;
    return this;
  }

  public StringList getActions() {
    return actions;
  }

  public PrivilegeEntity setActions(StringList actions) {
    this.actions = actions;
    return this;
  }

  public String getModuleName() {
    return moduleName;
  }

  public PrivilegeEntity setModuleName(String moduleName) {
    this.moduleName = moduleName;
    return this;
  }

  public String getModuleCode() {
    return moduleCode;
  }

  public PrivilegeEntity setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public PrivilegeEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }
}