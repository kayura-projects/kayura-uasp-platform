/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
