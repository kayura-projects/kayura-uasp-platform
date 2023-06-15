package org.kayura.cmd;

public interface CommandHandler<T extends Command, R> {
  R execute(T command);
}
