package org.kayura.uasp.common;

import java.util.Set;

public class IdPayload {

  private String id;
  private Set<String> ids;

  public static IdPayload create() {
    return new IdPayload();
  }

  public String getId() {
    return id;
  }

  public IdPayload setId(String id) {
    this.id = id;
    return this;
  }

  public Set<String> getIds() {
    return ids;
  }

  public IdPayload setIds(Set<String> ids) {
    this.ids = ids;
    return this;
  }
}
