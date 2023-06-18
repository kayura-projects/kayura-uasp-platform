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

package org.kayura.uasp.workflow;

import java.util.HashMap;

public class ChooseFlowQry {

  private String tenantId;
  private String formCode;
  private HashMap<String, Object> variables;

  public static ChooseFlowQry create() {
    return new ChooseFlowQry();
  }

  public String getFormCode() {
    return formCode;
  }

  public ChooseFlowQry setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public ChooseFlowQry setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public HashMap<String, Object> getVariables() {
    return variables;
  }

  public ChooseFlowQry setVariables(HashMap<String, Object> variables) {
    this.variables = variables;
    return this;
  }
}
