package org.kayura.type;

import java.util.ArrayList;
import java.util.List;

public class OptionGroup {

  private String text;
  private List<OptionItem> items = new ArrayList<>();
  private Object data;
  private String styleClass;

  public static OptionGroup builder() {
    return new OptionGroup();
  }

  public String getText() {
    return text;
  }

  public OptionGroup setText(String text) {
    this.text = text;
    return this;
  }

  public List<OptionItem> getItems() {
    return items;
  }

  public OptionGroup setItems(List<OptionItem> items) {
    this.items = items;
    return this;
  }

  public OptionGroup addItem(OptionItem item) {
    this.items.add(item);
    return this;
  }

  public Object getData() {
    return data;
  }

  public OptionGroup setData(Object data) {
    this.data = data;
    return this;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public OptionGroup setStyleClass(String styleClass) {
    this.styleClass = styleClass;
    return this;
  }
}
