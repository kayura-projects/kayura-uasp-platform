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

package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.ApiCommand;
import org.kayura.uasp.passport.SmsVerifyPayload;

public class SendSmsVerifyCommand extends ApiCommand {

  private String template;
  private SmsVerifyPayload payload;

  public SmsVerifyPayload getPayload() {
    return payload;
  }

  public SendSmsVerifyCommand setPayload(SmsVerifyPayload payload) {
    this.payload = payload;
    return this;
  }

  public String getTemplate() {
    return template;
  }

  public SendSmsVerifyCommand setTemplate(String template) {
    this.template = template;
    return this;
  }
}
