/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

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
