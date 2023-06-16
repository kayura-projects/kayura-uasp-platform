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

package org.kayura.uasp.company;

import org.kayura.type.UsableStatus;
import org.kayura.vaildation.Create;
import org.kayura.vaildation.Update;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CompanyApplicPayload {

  @NotBlank(groups = Update.class)
  private String id;
  @NotBlank(message = "必需指定应用ID。", groups = Create.class)
  private String appId;
  @NotBlank(message = "必需指定公司ID。", groups = Create.class)
  private String companyId;
  private LocalDateTime expireTime;
  private UsableStatus status;

  public static CompanyApplicPayload create() {
    return new CompanyApplicPayload();
  }

  public String getAppId() {
    return appId;
  }

  public CompanyApplicPayload setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCompanyId() {
    return companyId;
  }

  public CompanyApplicPayload setCompanyId(String companyId) {
    this.companyId = companyId;
    return this;
  }

  public LocalDateTime getExpireTime() {
    return expireTime;
  }

  public CompanyApplicPayload setExpireTime(LocalDateTime expireTime) {
    this.expireTime = expireTime;
    return this;
  }

  public UsableStatus getStatus() {
    return status;
  }

  public CompanyApplicPayload setStatus(UsableStatus status) {
    this.status = status;
    return this;
  }

  public String getId() {
    return id;
  }

  public CompanyApplicPayload setId(String id) {
    this.id = id;
    return this;
  }
}
