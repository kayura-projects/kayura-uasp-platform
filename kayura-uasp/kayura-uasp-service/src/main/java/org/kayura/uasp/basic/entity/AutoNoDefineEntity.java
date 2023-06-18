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