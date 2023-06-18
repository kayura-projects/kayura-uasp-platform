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
