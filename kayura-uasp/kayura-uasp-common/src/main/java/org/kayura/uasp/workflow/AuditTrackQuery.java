/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.uasp.workflow;

import java.util.HashMap;

public class AuditTrackQuery {

  private String businessKey;
  private String userId;
  private String tenantId;
  private String formCode;
  private HashMap<String, Object> variables;

  public static AuditTrackQuery create() {
    return new AuditTrackQuery();
  }

  public String getBusinessKey() {
    return businessKey;
  }

  public AuditTrackQuery setBusinessKey(String businessKey) {
    this.businessKey = businessKey;
    return this;
  }

  public String getUserId() {
    return userId;
  }

  public AuditTrackQuery setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public String getTenantId() {
    return tenantId;
  }

  public AuditTrackQuery setTenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public String getFormCode() {
    return formCode;
  }

  public AuditTrackQuery setFormCode(String formCode) {
    this.formCode = formCode;
    return this;
  }

  public HashMap<String, Object> getVariables() {
    return variables;
  }

  public AuditTrackQuery setVariables(HashMap<String, Object> variables) {
    this.variables = variables;
    return this;
  }
}
