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
import org.kayura.uasp.file.UploadPayload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUploadCommand extends Command {

  private MultipartFile file;
  private UploadPayload payload;
  private List<String> editableTypes;

  public MultipartFile getFile() {
    return file;
  }

  public FileUploadCommand setFile(MultipartFile file) {
    this.file = file;
    return this;
  }

  public UploadPayload getPayload() {
    return payload;
  }

  public FileUploadCommand setPayload(UploadPayload payload) {
    this.payload = payload;
    return this;
  }

  public List<String> getEditableTypes() {
    return editableTypes;
  }

  public FileUploadCommand setEditableTypes(List<String> editableTypes) {
    this.editableTypes = editableTypes;
    return this;
  }
}
