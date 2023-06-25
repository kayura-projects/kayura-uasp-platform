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
