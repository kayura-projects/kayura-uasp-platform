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

import org.kayura.data.Entity;
import org.kayura.mybatis.annotation.mapper.*;

/**
 * 自动编号定义(uasp_auto_no_define) 实体定义
 *
 * @author liangXia@live.com
 */
@Table("uasp_auto_no_define")
public class AutoNoDefineEntity implements Entity {

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