package org.kayura.skeleton;

import javax.validation.constraints.NotBlank;

public class BlankPayload {

  @NotBlank
  private String code;
  @NotBlank
  private String name;

  public String getCode() {
    return code;
  }

  public BlankPayload setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public BlankPayload setName(String name) {
    this.name = name;
    return this;
  }
}
