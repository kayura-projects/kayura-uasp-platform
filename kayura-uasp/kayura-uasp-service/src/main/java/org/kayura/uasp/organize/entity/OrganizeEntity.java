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
