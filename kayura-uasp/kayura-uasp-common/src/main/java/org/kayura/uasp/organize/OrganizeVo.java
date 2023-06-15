package org.kayura.uasp.organize;

public class OrganizeVo {

  private String id;
  private String code;
  private String name;
  private OrganizeTypes type;
  private Integer sort;

  public static OrganizeVo create() {
    return new OrganizeVo();
  }

  public String getId() {
    return id;
  }

  public OrganizeVo setId(String id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public OrganizeVo setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public OrganizeVo setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public OrganizeVo setSort(Integer sort) {
    this.sort = sort;
    return this;
  }

  public OrganizeTypes getType() {
    return type;
  }

  public OrganizeVo setType(OrganizeTypes type) {
    this.type = type;
    return this;
  }
}
