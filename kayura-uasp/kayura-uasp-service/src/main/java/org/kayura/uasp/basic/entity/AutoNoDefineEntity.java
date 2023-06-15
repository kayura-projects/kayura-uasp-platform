package org.kayura.uasp.basic.entity;

import org.kayura.mybatis.annotation.mapper.*;

/**
 * 自动编号定义(uasp_auto_no_define) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_auto_no_define")
public class AutoNoDefineEntity {

  /** 自动编号ID */
  @Id
  private String defineId;
  /** 应用ID */
  private String appId;
  /** 编号 */
  private String code;
  /** 显示名 */
  private String name;

  public static AutoNoDefineEntity create() {
    return new AutoNoDefineEntity();
  }

  public String getDefineId() {
    return defineId;
  }

  public AutoNoDefineEntity setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getAppId() {
    return appId;
  }

  public AutoNoDefineEntity setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getCode() {
    return code;
  }

  public AutoNoDefineEntity setCode(String code) {
    this.code = code;
    return this;
  }

  public String getName() {
    return name;
  }

  public AutoNoDefineEntity setName(String name) {
    this.name = name;
    return this;
  }

}