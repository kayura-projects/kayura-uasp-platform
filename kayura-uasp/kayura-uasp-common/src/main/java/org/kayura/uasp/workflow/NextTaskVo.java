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
