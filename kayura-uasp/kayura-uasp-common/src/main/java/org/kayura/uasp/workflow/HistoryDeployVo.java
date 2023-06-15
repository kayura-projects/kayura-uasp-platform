package org.kayura.uasp.workflow;

import java.time.LocalDateTime;

public class HistoryDeployVo {

  private String defineId;
  private String deploymentId;
  private String name;
  private LocalDateTime deployTime;
  private Integer version;
  private Integer instances;

  public static HistoryDeployVo create() {
    return new HistoryDeployVo();
  }

  public String getDefineId() {
    return defineId;
  }

  public HistoryDeployVo setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getName() {
    return name;
  }

  public HistoryDeployVo setName(String name) {
    this.name = name;
    return this;
  }

  public LocalDateTime getDeployTime() {
    return deployTime;
  }

  public HistoryDeployVo setDeployTime(LocalDateTime deployTime) {
    this.deployTime = deployTime;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public HistoryDeployVo setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public Integer getInstances() {
    return instances;
  }

  public HistoryDeployVo setInstances(Integer instances) {
    this.instances = instances;
    return this;
  }

  public String getDeploymentId() {
    return deploymentId;
  }

  public HistoryDeployVo setDeploymentId(String deploymentId) {
    this.deploymentId = deploymentId;
    return this;
  }
}
