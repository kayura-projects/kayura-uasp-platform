package org.kayura.uasp.applic;

import javax.validation.constraints.NotBlank;

public class IdQry {

  @NotBlank(message = "缺少 id 条件。")
  private String id;

  public static IdQry create() {
    return new IdQry();
  }

  public String getId() {
    return id;
  }

  public IdQry setId(String id) {
    this.id = id;
    return this;
  }
}
