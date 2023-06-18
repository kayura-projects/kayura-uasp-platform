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

package org.kayura.uasp.ip;

import java.time.LocalDateTime;

public class IpStoreVo {

  private String ipAddr;
  private String ipDesc;
  private IPTypes type;
  private LocalDateTime updateTime;

  public String getIpAddr() {
    return ipAddr;
  }

  public IpStoreVo setIpAddr(String ipAddr) {
    this.ipAddr = ipAddr;
    return this;
  }

  public String getIpDesc() {
    return ipDesc;
  }

  public IpStoreVo setIpDesc(String ipDesc) {
    this.ipDesc = ipDesc;
    return this;
  }

  public IPTypes getType() {
    return type;
  }

  public IpStoreVo setType(IPTypes type) {
    this.type = type;
    return this;
  }

  public LocalDateTime getUpdateTime() {
    return updateTime;
  }

  public IpStoreVo setUpdateTime(LocalDateTime updateTime) {
    this.updateTime = updateTime;
    return this;
  }
}
