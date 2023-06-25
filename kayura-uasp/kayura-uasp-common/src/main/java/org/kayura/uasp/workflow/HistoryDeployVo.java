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
