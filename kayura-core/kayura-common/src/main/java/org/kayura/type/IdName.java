package org.kayura.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IdName {

  public static List<IdName> EMPTY = Collections.unmodifiableList(new ArrayList<>());

  private String id;
  private String name;

  public static IdName create() {
    return new IdName();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    IdName idName = (IdName) o;
    return id.equals(idName.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public String getId() {
    return id;
  }

  public IdName setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public IdName setName(String name) {
    this.name = name;
    return this;
  }
}
