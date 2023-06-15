package org.kayura.uasp.workflow;

public class FlowLabel {

  private String code;
  private String name;
  private FlowStatus status = FlowStatus.NOT;
  private Integer sort;

  public static FlowLabel create() {
    return new FlowLabel();
  }

  public static FlowLabel clone(FlowLabel source) {
    return create().setCode(source.code).setName(source.name).setSort(source.sort);
  }

  public String getCode() {
    return code;
  }

  public FlowLabel setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public FlowLabel setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public FlowLabel setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public FlowStatus getStatus() {
    return status;
  }

  public FlowLabel setStatus(FlowStatus status) {
    this.status = status;
    return this;
  }
}
