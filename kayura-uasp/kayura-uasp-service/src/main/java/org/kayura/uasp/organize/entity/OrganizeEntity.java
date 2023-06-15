package org.kayura.uasp.organize.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.organize.OrganizeTypes;

@Table("uasp_organize")
public class OrganizeEntity {

  @Id
  private String id;
  private String parentId;
  private String code;
  private String name;
  private OrganizeTypes type;
  private Integer sort;

  public static OrganizeEntity create() {
    return new OrganizeEntity();
  }

  public String getId() {
    return id;
  }

  public OrganizeEntity setId(String id) {
    this.id = id;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public OrganizeEntity setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public OrganizeEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public OrganizeEntity setName(String name) {
    this.name = name;
    return this;
  }

  public OrganizeTypes getType() {
    return type;
  }

  public OrganizeEntity setType(OrganizeTypes type) {
    this.type = type;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public OrganizeEntity setSort(Integer sort) {
    this.sort = sort;
    return this;
  }
}
