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

package org.kayura.uasp.organize.entity;

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;
import org.kayura.uasp.organize.OrganizeTypes;

@Table("uasp_organize")
public class OrganizeEntity implements Entity {

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
