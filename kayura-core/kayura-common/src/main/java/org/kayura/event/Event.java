package org.kayura.event;

public abstract class Event implements IEvent {

  private Object source;

  protected Event(Object source){
    this.source = source;
  }

  public Object getSource() {
    return source;
  }

  public void setSource(Object source) {
    this.source = source;
  }
}
