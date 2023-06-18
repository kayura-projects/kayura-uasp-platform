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
