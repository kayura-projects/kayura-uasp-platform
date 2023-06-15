/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

package org.kayura.uasp.user;

import org.kayura.type.EnumValue;

public enum ChangeModes implements EnumValue {

  NEXT("NEXT", "下次登录时修改密码"),
  AUTO("AUTO", "自动生成新密码"),
  MANUAL("MANUAL", "现在手工设定密码");

  private final String value;
  private final String name;

  ChangeModes(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  public String getName() {
    return name;
  }
}
