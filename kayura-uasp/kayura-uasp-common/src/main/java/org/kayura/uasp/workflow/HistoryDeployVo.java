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
