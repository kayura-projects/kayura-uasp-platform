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

public class ChooseFlowVo {

  private String processKey;
  private String displayName;
  private String description;
  private Boolean primary;
  private Integer lastVersion;

  public static ChooseFlowVo create() {
    return new ChooseFlowVo();
  }

  public String getProcessKey() {
    return processKey;
  }

  public ChooseFlowVo setProcessKey(String processKey) {
    this.processKey = processKey;
    return this;
  }

  public String getDisplayName() {
    return displayName;
  }

  public ChooseFlowVo setDisplayName(String displayName) {
    this.displayName = displayName;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public ChooseFlowVo setDescription(String description) {
    this.description = description;
    return this;
  }

  public Boolean getPrimary() {
    return primary;
  }

  public ChooseFlowVo setPrimary(Boolean primary) {
    this.primary = primary;
    return this;
  }

  public Integer getLastVersion() {
    return lastVersion;
  }

  public ChooseFlowVo setLastVersion(Integer lastVersion) {
    this.lastVersion = lastVersion;
    return this;
  }
}
