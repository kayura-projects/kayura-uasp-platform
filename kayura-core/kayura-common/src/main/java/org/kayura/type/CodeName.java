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
