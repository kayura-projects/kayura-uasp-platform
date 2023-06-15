package org.kayura.cmd;

public interface CommandBus {
  <R> R dispatch(Command command);
}
