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

package org.kayura.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CodeName {

  public static List<CodeName> EMPTY = Collections.unmodifiableList(new ArrayList<>());

  private String code;
  private String name;

  public CodeName() {
  }

  public CodeName(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public static CodeName create() {
    return new CodeName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CodeName codeName = (CodeName) o;
    return code.equals(codeName.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  public String getCode() {
    return code;
  }

  public CodeName setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public CodeName setName(String name) {
    this.name = name;
    return this;
  }
}
