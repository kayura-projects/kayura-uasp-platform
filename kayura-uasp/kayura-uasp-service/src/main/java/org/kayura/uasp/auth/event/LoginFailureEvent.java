package org.kayura.uasp.auth.event;

import org.kayura.event.Event;

public class LoginFailureEvent extends Event {

  private final Exception exception;

  public LoginFailureEvent(Object source, Exception exception) {
    super(source);
    this.exception = exception;
  }

  public Exception getException() {
    return exception;
  }

}
