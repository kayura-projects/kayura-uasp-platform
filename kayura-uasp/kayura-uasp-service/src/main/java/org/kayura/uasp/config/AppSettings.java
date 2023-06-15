/*------------------------------------------------------------------------------
 - 版权所属 2019 Liang.Xia 及 原作者
 - 您可以在 Apache License 2.0 版下获得许可副本，
 - 同时必须保证分发的本软件是在“原始”的基础上分发的。
 - 除非适用法律要求或书面同意。
 - http://www.apache.org/licenses/LICENSE-2.0
 - 请参阅许可证中控制权限和限制的特定语言。
 -----------------------------------------------------------------------------*/
package org.kayura.uasp.config;

import org.kayura.uasp.utils.UaspConstants;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kayura.app")
public class AppSettings {

  // 当前应用程序代码
  private String appCode = UaspConstants.UASP_APP_CODE;
  // 密码过期时间（秒）
  private long pwdExpire = 7776000L;

  public String getAppCode() {
    return appCode;
  }

  public AppSettings setAppCode(String appCode) {
    this.appCode = appCode;
    return this;
  }

  public long getPwdExpire() {
    return this.pwdExpire;
  }

  public void setPwdExpire(long pwdExpire) {
    this.pwdExpire = pwdExpire;
  }


}
