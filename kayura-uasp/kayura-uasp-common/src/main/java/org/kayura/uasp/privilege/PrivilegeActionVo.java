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
