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

package org.kayura.uasp.privilege;

import org.kayura.type.CodeName;
import org.kayura.uasp.func.ActionTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrivilegeActionVo {

  private String code;
  private String name;
  private ActionTypes type;
  private Boolean checked;
  private List<CodeName> source;

  public static PrivilegeActionVo create() {
    return new PrivilegeActionVo();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PrivilegeActionVo that = (PrivilegeActionVo) o;
    return code.equals(that.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code);
  }

  public String getCode() {
    return code;
  }

  public PrivilegeActionVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public PrivilegeActionVo setName(String name) {
    this.name = name;
    return this;
  }

  public List<CodeName> getSource() {
    return source;
  }

  public PrivilegeActionVo setSource(List<CodeName> source) {
    this.source = source;
    return this;
  }

  public PrivilegeActionVo addSource(String code, String name) {
    if (this.source == null) {
      this.source = new ArrayList<>();
    }
    this.source.add(new CodeName(code, name));
    return this;
  }

  public Boolean getChecked() {
    return checked;
  }

  public PrivilegeActionVo setChecked(Boolean checked) {
    this.checked = checked;
    return this;
  }

  public ActionTypes getType() {
    return type;
  }

  public PrivilegeActionVo setType(ActionTypes type) {
    this.type = type;
    return this;
  }
}
