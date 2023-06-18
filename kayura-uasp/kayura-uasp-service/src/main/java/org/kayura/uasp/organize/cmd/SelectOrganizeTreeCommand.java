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

package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.Command;
import org.kayura.type.DataStatus;
import org.kayura.uasp.utils.OutputTypes;

public class SelectOrganizeTreeCommand extends Command {

  private OutputTypes output;
  private String companyId;
  private Integer level;
  private DataStatus status;

  public OutputTypes getOutput() {
    return output;
  }

  public SelectOrganizeTreeCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public SelectOrganizeTreeCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Integer getLevel() {
    return level;
  }

  public SelectOrganizeTreeCommand setLevel(Integer level) {
    this.level = level;
    return this;
  }

  public DataStatus getStatus() {
    return status;
  }

  public SelectOrganizeTreeCommand setStatus(DataStatus status) {
    this.status = status;
    return this;
  }
}
