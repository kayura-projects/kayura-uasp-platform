/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.workflow;

import org.kayura.type.Properties;

import java.util.ArrayList;
import java.util.List;

public class NextTaskVo {

  private String key;
  private String name;
  private Boolean checked;
  private Properties properties;
  private List<IdentityLinkVo> identityLinks = new ArrayList<>();

  public static NextTaskVo create() {
    return new NextTaskVo();
  }

  public String getKey() {
    return key;
  }

  public NextTaskVo setKey(String key) {
    this.key = key;
    return this;
  }

  public String getName() {
    return name;
  }

  public NextTaskVo setName(String name) {
    this.name = name;
    return this;
  }

  public Boolean getChecked() {
    return checked;
  }

  public NextTaskVo setChecked(Boolean checked) {
    this.checked = checked;
    return this;
  }


  public Properties getProperties() {
    return properties;
  }

  public NextTaskVo setProperties(Properties properties) {
    this.properties = properties;
    return this;
  }

  public NextTaskVo addUser(String id, String name) {
    if (this.identityLinks.stream().noneMatch(x -> x.isUser() && id.equals(x.getUserId()))) {
      this.identityLinks.add(IdentityLinkVo.create().setUserId(id).setUserName(name));
    }
    return this;
  }

  public NextTaskVo addGroup(String id, String name) {
    if (this.identityLinks.stream().noneMatch(x -> x.isGroup() && id.equals(x.getGroupId()))) {
      this.identityLinks.add(IdentityLinkVo.create().setGroupId(id).setGroupName(name));
    }
    return this;
  }

  public List<IdentityLinkVo> getIdentityLinks() {
    return identityLinks;
  }

  public NextTaskVo setIdentityLinks(List<IdentityLinkVo> identityLinks) {
    this.identityLinks = identityLinks;
    return this;
  }
}
