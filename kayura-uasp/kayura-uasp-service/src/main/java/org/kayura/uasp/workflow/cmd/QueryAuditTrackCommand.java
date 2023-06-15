package org.kayura.uasp.workflow.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.workflow.AuditTrackQuery;

public class QueryAuditTrackCommand extends Command {

  private AuditTrackQuery query;

  public AuditTrackQuery getQuery() {
    return query;
  }

  public QueryAuditTrackCommand setQuery(AuditTrackQuery query) {
    this.query = query;
    return this;
  }
}
