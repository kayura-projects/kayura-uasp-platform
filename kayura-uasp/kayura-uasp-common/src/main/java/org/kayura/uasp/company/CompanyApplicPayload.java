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
