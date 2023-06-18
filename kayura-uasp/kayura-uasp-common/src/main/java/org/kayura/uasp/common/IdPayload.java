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

package org.kayura.uasp.common;

import java.util.Set;

public class IdPayload {

  private String id;
  private Set<String> ids;

  public static IdPayload create() {
    return new IdPayload();
  }

  public String getId() {
    return id;
  }

  public IdPayload setId(String id) {
    this.id = id;
    return this;
  }

  public Set<String> getIds() {
    return ids;
  }

  public IdPayload setIds(Set<String> ids) {
    this.ids = ids;
    return this;
  }
}
