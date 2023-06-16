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

package org.kayura.uasp.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangeUserPasswordPayload {

  @NotBlank
  private String userId;
  @NotNull
  private ChangeModes changeMode;
  private String password;
  @NotNull
  private NotifyModes notifyMode;

  public static ChangeUserPasswordPayload create() {
    return new ChangeUserPasswordPayload();
  }

  public String getUserId() {
    return userId;
  }

  public ChangeUserPasswordPayload setUserId(String userId) {
    this.userId = userId;
    return this;
  }

  public ChangeModes getChangeMode() {
    return changeMode;
  }

  public ChangeUserPasswordPayload setChangeMode(ChangeModes changeMode) {
    this.changeMode = changeMode;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public ChangeUserPasswordPayload setPassword(String password) {
    this.password = password;
    return this;
  }

  public NotifyModes getNotifyMode() {
    return notifyMode;
  }

  public ChangeUserPasswordPayload setNotifyMode(NotifyModes notifyMode) {
    this.notifyMode = notifyMode;
    return this;
  }
}
