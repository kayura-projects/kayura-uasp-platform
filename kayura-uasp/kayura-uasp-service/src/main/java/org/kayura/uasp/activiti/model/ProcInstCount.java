package org.kayura.uasp.activiti.model;

public class ProcInstCount {

  private String defineId;
  private Integer count;

  public static ProcInstCount builder() {
    return new ProcInstCount();
  }

  public String getDefineId() {
    return defineId;
  }

  public ProcInstCount setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public Integer getCount() {
    return count;
  }

  public ProcInstCount setCount(Integer count) {
    this.count = count;
    return this;
  }
}
