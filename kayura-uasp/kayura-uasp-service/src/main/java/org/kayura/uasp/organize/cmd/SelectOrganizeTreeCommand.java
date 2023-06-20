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
import org.kayura.type.DataStatus;
import org.kayura.uasp.utils.OutputTypes;

public class SelectOrganizeTreeCommand extends ApiCommand {

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
