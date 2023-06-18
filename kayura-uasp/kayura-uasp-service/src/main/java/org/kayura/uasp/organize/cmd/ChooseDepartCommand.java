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
import org.kayura.uasp.utils.OutputTypes;

import java.util.Set;

public class ChooseDepartCommand extends Command {

  private OutputTypes output;
  private String companyId;
  private Set<String> exclusions;

  public String getCompanyId() {
    return companyId;
  }

  public ChooseDepartCommand setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public Set<String> getExclusions() {
    return exclusions;
  }

  public ChooseDepartCommand setExclusions(Set<String> exclusions) {
    this.exclusions = exclusions;
    return this;
  }

  public OutputTypes getOutput() {
    return output;
  }

  public ChooseDepartCommand setOutput(OutputTypes output) {
    this.output = output;
    return this;
  }
}
