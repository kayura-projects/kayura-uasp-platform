package org.kayura.uasp.workflow;

import java.util.List;

public class BpmnTrackVo {

  private String bpmnXml;
  private List<String> completes; // 任务、网关
  private List<String> flows; // 连接线
  private List<String> currents;  // 任务

  public static BpmnTrackVo create() {
    return new BpmnTrackVo();
  }

  public String getBpmnXml() {
    return bpmnXml;
  }

  public BpmnTrackVo setBpmnXml(String bpmnXml) {
    this.bpmnXml = bpmnXml;
    return this;
  }

  public List<String> getCompletes() {
    return completes;
  }

  public BpmnTrackVo setCompletes(List<String> completes) {
    this.completes = completes;
    return this;
  }

  public List<String> getCurrents() {
    return currents;
  }

  public BpmnTrackVo setCurrents(List<String> currents) {
    this.currents = currents;
    return this;
  }

  public List<String> getFlows() {
    return flows;
  }

  public BpmnTrackVo setFlows(List<String> flows) {
    this.flows = flows;
    return this;
  }
}
