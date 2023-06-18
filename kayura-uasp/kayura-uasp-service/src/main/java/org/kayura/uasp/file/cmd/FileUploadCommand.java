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
