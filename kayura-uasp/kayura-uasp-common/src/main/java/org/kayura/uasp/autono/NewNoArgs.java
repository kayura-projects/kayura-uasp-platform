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

package org.kayura.uasp.autono;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

public class NewNoArgs {

  @NotBlank
  private String code;
  private String tenantId;
  private Map<String, String> params;

  public static NewNoArgs create() {
    return new NewNoArgs();
  }

  public String getTenantId() {
    return tenantId;
  }

  public NewNoArgs setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public Map<String, String> getParams() {
    return params;
  }

  public NewNoArgs setParams(Map<String, String> params) {
    this.params = params;
    return this;
  }

  public NewNoArgs putParam(String key, String value) {
    if (this.params == null) {
      this.params = new HashMap<>();
    }
    this.params.put(key, value);
    return this;
  }

  public String getCode() {
    return code;
  }

  public NewNoArgs setCode(String code) {
    this.code = code;
    return this;
  }
}
