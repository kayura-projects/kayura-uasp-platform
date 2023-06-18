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

package org.kayura.uasp.func;

import java.util.List;

/**
 * 模块导入数据结构。
 */
public class ImportVo {

  private String appId;
  private String parentId;
  private List<String> moduleIds;

  public String getAppId() {
    return appId;
  }

  public ImportVo setAppId(String appId) {
    this.appId = appId;
    return this;
  }

  public String getParentId() {
    return parentId;
  }

  public ImportVo setParentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  public List<String> getModuleIds() {
    return moduleIds;
  }

  public ImportVo setModuleIds(List<String> moduleIds) {
    this.moduleIds = moduleIds;
    return this;
  }
}
