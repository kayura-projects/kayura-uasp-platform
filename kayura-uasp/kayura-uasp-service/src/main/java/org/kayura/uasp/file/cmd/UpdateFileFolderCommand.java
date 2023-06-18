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

package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.file.FolderPayload;

public class UpdateFileFolderCommand extends Command {

  private String folderId;
  private FolderPayload payload;

  public String getFolderId() {
    return folderId;
  }

  public UpdateFileFolderCommand setFolderId(String folderId) {
    this.folderId = folderId;
    return this;
  }

  public FolderPayload getPayload() {
    return payload;
  }

  public UpdateFileFolderCommand setPayload(FolderPayload payload) {
    this.payload = payload;
    return this;
  }
}
