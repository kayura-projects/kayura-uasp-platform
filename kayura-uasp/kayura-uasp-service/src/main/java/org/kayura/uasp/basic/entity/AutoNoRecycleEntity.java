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

package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.Id;
import org.kayura.mybatis.annotation.mapper.Table;

/**
 * 编号回收站(uasp_auto_no_recycle) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_auto_no_recycle")
public class AutoNoRecycleEntity {

  /** 计数周期 */
  @Id
  private String countId;
  /** 回收编号 */
  @Id
  private String recycleNo;

  public static AutoNoRecycleEntity create() {
    return new AutoNoRecycleEntity();
  }

  public String getCountId() {
    return countId;
  }

  public AutoNoRecycleEntity setCountId(String countId) {
    this.countId = countId;
    return this;
  }

  public String getRecycleNo() {
    return recycleNo;
  }

  public AutoNoRecycleEntity setRecycleNo(String recycleNo) {
    this.recycleNo = recycleNo;
    return this;
  }
}