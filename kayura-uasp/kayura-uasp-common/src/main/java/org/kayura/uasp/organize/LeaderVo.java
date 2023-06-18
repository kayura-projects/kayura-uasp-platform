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

package org.kayura.uasp.organize;

public class LeaderVo {

  private String leaderId;
  private String leaderName;
  private String duty;

  public static LeaderVo create() {
    return new LeaderVo();
  }

  public String getLeaderId() {
    return leaderId;
  }

  public LeaderVo setLeaderId(String leaderId) {
    this.leaderId = leaderId;
    return this;
  }

  public String getLeaderName() {
    return leaderName;
  }

  public LeaderVo setLeaderName(String leaderName) {
    this.leaderName = leaderName;
    return this;
  }

  public String getDuty() {
    return duty;
  }

  public LeaderVo setDuty(String duty) {
    this.duty = duty;
    return this;
  }
}
