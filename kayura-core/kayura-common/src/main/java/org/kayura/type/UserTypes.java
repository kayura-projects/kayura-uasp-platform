package org.kayura.type;

/**
 * 用户类型:ROOT,ADMIN,MANAGER,USER,CLIENT;
 */
public enum UserTypes implements EnumValue {

  ROOT("ROOT", "超级用户"),
  ADMIN("ADMIN", "系统管理员"),
  MANAGER("MANAGER", "公司管理员"),
  USER("USER", "业务操作员"),
  CLIENT("CLIENT", "客户端");

  private final String value;
  private final String name;

  UserTypes(final String value, String name) {
    this.value = value;
    this.name = name;
  }

  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public String getName() {
    return name;
  }
}
