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

package org.kayura.uasp.file;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MoveFilePayload {

  @NotEmpty
  private List<String> linkIds;
  @NotBlank
  private String folderId;

  public List<String> getLinkIds() {
    return linkIds;
  }

  public MoveFilePayload setLinkIds(List<String> linkIds) {
    this.linkIds = linkIds;
    return this;
  }

  public String getFolderId() {
    return folderId;
  }

  public MoveFilePayload setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }
}
