package org.kayura.uasp.file.handler;

import org.kayura.uasp.file.service.FileService;
import org.kayura.uasp.handler.UploadHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileExistStatusUpdate {

  private final UploadHandler uploadHandler;
  private final FileService fileService;

  public FileExistStatusUpdate(UploadHandlerDelegate uploadHandler,
                               FileService fileService) {
    this.uploadHandler = uploadHandler.getAlive();
    this.fileService = fileService;
  }

  public void execute() {
    List<String> fileIds = uploadHandler.storeFileNames();
    this.fileService.correctFileExists(fileIds);
  }

}
