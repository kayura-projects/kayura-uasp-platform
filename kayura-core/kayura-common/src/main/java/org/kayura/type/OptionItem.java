/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
