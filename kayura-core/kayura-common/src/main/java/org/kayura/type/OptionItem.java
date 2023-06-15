/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
