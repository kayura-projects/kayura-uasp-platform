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

/**
 * 下拉列表项数据结构。
 */
public class OptionItem {

  private String id;
  private String text;
  private Boolean selected;
  private Object data;
  private String styleClass;

  public static OptionItem builder() {
    return new OptionItem();
  }

  public String getId() {
    return id;
  }

  public OptionItem setId(String id) {
    this.id = id;
    return this;
  }

  public String getText() {
    return text;
  }

  public OptionItem setText(String text) {
    this.text = text;
    return this;
  }

  public Boolean getSelected() {
    return selected;
  }

  public OptionItem setSelected(Boolean selected) {
    this.selected = selected;
    return this;
  }

  public Object getData() {
    return data;
  }

  public OptionItem setData(Object data) {
    this.data = data;
    return this;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public OptionItem setStyleClass(String styleClass) {
    this.styleClass = styleClass;
    return this;
  }
}
