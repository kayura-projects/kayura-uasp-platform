package org.kayura.uasp.file.handler;

import org.kayura.except.ConfigException;
import org.kayura.uasp.file.StoreTypes;
import org.kayura.uasp.handler.UploadHandler;

import java.util.List;

public class UploadHandlerDelegate {

  private final List<UploadHandler> handlers;
  private final UploadHandler alive;

  public UploadHandlerDelegate(List<UploadHandler> handlers, UploadHandler alive) {
    this.handlers = handlers;
    this.alive = alive;
  }

  public UploadHandler getProvider(StoreTypes storeType) {
    return handlers.stream()
      .filter(a -> a.getStoreType().equals(storeType))
      .findFirst()
      .orElseThrow(ConfigException::new);
  }

  public UploadHandler getAlive() {
    return alive;
  }

}
