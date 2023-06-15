package org.kayura.example.listener;

import org.kayura.uasp.file.handler.FileExistStatusUpdate;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationReadyRunner implements ApplicationRunner {

  private final FileExistStatusUpdate statusUpdate;

  public ApplicationReadyRunner(FileExistStatusUpdate statusUpdate) {
    this.statusUpdate = statusUpdate;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    statusUpdate.execute();
  }

}
