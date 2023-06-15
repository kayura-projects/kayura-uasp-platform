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
