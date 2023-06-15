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