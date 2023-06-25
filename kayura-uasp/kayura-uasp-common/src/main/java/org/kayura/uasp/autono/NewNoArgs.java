/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
