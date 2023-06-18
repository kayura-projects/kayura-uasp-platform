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
