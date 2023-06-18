/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
