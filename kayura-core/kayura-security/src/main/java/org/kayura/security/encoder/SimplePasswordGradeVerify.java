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

package org.kayura.security.encoder;

import org.kayura.type.Result;
import org.kayura.utils.StringUtils;

/**
 * 内置的简单密码等级验证。
 * 1、密码不允许为空。
 * 2、密码长度必需大于等于 6 位。
 */
public class SimplePasswordGradeVerify implements PasswordGradeVerify {

  private int minLen = 6;

  @Override
  public Result verify(String password) {

    if (StringUtils.isBlank(password)) {
      return Result.fail("密码不允许为空。");
    }
    if (password.length() < minLen) {
      return Result.fail("密码长度必需大于等于 " + minLen + " 位。");
    }
    return Result.ok();
  }

  public void setMinLen(int minLen) {
    this.minLen = minLen;
  }
}
