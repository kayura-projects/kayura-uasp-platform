package org.kayura.uasp.file.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.file.DownloadQuery;

public class FileDownloadCommand extends Command {

  private DownloadQuery query;

  public DownloadQuery getQuery() {
    return query;
  }

  public FileDownloadCommand setQuery(DownloadQuery query) {
    this.query = query;
    return this;
  }
}
