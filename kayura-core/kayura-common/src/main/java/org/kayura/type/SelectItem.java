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

import java.util.*;

/**
 * 复选框数据结构。
 */
public class SelectItem {

  public static List<SelectItem> EMPTY = Collections.unmodifiableList(new ArrayList<>());

  private String id;
  private String code;
  private String text;
  private String group;
  private Boolean checked;
  private Object data;
  private Boolean disabled;
  private String iconClass;
  private String styleClass;
  private Properties attrs;
  private String hint;

  public static SelectItem create() {
    return new SelectItem();
  }

  public String getId() {
    return id;
  }

  public SelectItem setId(String id) {
    this.id = id;
    return this;
  }

  public String getText() {
    return text;
  }

  public SelectItem setText(String text) {
    this.text = text;
    return this;
  }

  public Boolean getChecked() {
    return checked;
  }

  public SelectItem setChecked(Boolean checked) {
    this.checked = checked;
    return this;
  }

  public Object getData() {
    return data;
  }

  public SelectItem setData(Object data) {
    this.data = data;
    return this;
  }

  public Boolean getDisabled() {
    return disabled;
  }

  public SelectItem setDisabled(Boolean disabled) {
    this.disabled = disabled;
    return this;
  }

  public String getIconClass() {
    return iconClass;
  }

  public SelectItem setIconClass(String iconClass) {
    this.iconClass = iconClass;
    return this;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public SelectItem setStyleClass(String styleClass) {
    this.styleClass = styleClass;
    return this;
  }

  public String getCode() {
    return code;
  }

  public SelectItem setCode(String code) {
    this.code = code;
    return this;
  }

  public Properties getAttrs() {
    return attrs;
  }

  public SelectItem setAttrs(Properties attrs) {
    this.attrs = attrs;
    return this;
  }

  public SelectItem put(String key, Object value) {
    if (this.attrs == null) {
      this.attrs = new Properties();
    }
    this.attrs.put(key, value);
    return this;
  }

  public String getHint() {
    return hint;
  }

  public SelectItem setHint(String hint) {
    this.hint = hint;
    return this;
  }

  public String getGroup() {
    return group;
  }

  public SelectItem setGroup(String group) {
    this.group = group;
    return this;
  }
}
