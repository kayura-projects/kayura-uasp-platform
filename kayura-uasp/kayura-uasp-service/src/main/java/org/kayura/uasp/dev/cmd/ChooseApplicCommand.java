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

package org.kayura.uasp.dev.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.applic.ApplicTypes;
import org.kayura.uasp.utils.OutputTypes;

public class ChooseApplicCommand extends Command {

  private OutputTypes output;
  private ApplicTypes type;
  private boolean notUasp;
  private String tenantId;
  private String companyId;
  private String userId;
  private Integer level;

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseApplicCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public ApplicTypes getType() {
    return type;
  }

  public ChooseApplicCommand setType(ApplicTypes type) {
    this.type = type;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseApplicCommand setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public ChooseApplicCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public ChooseApplicCommand setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public boolean isNotUasp() {
    return notUasp;
  }

  public ChooseApplicCommand setNotUasp(boolean notUasp) {
    this.notUasp = notUasp;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public ChooseApplicCommand setUserId(String userId) {
    this.userId = userId;
    return this;
  }
}
