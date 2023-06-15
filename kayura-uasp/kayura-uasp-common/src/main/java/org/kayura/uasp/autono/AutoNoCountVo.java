package org.kayura.uasp.autono;

import java.util.List;

public class AutoNoCountVo {

  private String countId;
  private String cycleValue;
  private Integer countValue;
  private List<String> recycleNos;

  public static AutoNoCountVo create() {
    return new AutoNoCountVo();
  }

  public String getCycleValue() {
    return cycleValue;
  }

  public AutoNoCountVo setCycleValue(String cycleValue) {
    this.cycleValue = cycleValue;
    return this;
  }

  public Integer getCountValue() {
    return countValue;
  }

  public AutoNoCountVo setCountValue(Integer countValue) {
    this.countValue = countValue;
    return this;
  }

  public List<String> getRecycleNos() {
    return recycleNos;
  }

  public AutoNoCountVo setRecycleNos(List<String> recycleNos) {
    this.recycleNos = recycleNos;
    return this;
  }

  public String getCountId() {
    return countId;
  }

  public AutoNoCountVo setCountId(String countId) {
    this.countId = countId;
    return this;
  }
}
