/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
