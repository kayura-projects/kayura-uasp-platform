/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
