/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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