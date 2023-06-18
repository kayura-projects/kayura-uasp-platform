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
