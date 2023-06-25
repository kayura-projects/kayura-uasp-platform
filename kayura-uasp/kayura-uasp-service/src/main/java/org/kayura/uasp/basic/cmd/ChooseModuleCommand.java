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

package org.kayura.uasp.basic.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.uasp.func.ModuleTypes;

import java.util.Set;

public class ChooseModuleCommand extends ApiCommand {

  private String appId;
  private Set<ModuleTypes> types;

  public String getAppId() {
    return appId;
  }

  public ChooseModuleCommand setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public Set<ModuleTypes> getTypes() {
    return types;
  }

  public ChooseModuleCommand setTypes(Set<ModuleTypes> types) {
    this.types = types;
    return this;
  }

  public ChooseModuleCommand setTypes(ModuleTypes... types) {
    this.types = Set.of(types);
    return this;
  }
}
