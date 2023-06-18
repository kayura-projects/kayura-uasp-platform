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
