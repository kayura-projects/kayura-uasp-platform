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

package org.kayura.security.sneak;

import java.io.Serializable;

public class SneakItem implements Serializable {

  private Long expire;
  private String user;
  private String caller;

  public static SneakItem create() {
    return new SneakItem();
  }

  public Long getExpire() {
    return expire;
  }

  public SneakItem setExpire(Long expire) {
    this.expire = expire;
    return this;
  }

  public String getUser() {
    return user;
  }

  public SneakItem setUser(String user) {
    this.user = user;
    return this;
  }

  public String getCaller() {
    return caller;
  }

  public SneakItem setCaller(String caller) {
    this.caller = caller;
    return this;
  }
}
