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
