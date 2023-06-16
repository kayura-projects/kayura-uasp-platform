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
