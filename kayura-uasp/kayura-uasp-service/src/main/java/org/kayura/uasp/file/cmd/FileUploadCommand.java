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
