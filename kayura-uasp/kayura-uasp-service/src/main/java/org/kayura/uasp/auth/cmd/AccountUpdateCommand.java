package org.kayura.uasp.auth.cmd;

import org.kayura.cmd.Command;
import org.kayura.uasp.account.AccountPayload;

public class AccountUpdateCommand extends Command {

  private AccountPayload payload;

  public AccountPayload getPayload() {
    return payload;
  }

  public AccountUpdateCommand setPayload(AccountPayload payload) {
    this.payload = payload;
    return this;
  }
}
