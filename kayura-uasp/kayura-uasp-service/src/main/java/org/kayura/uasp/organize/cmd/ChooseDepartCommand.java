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

package org.kayura.uasp.organize.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.uasp.utils.OutputTypes;

import java.util.Set;

public class ChooseDepartCommand extends ApiCommand {

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
