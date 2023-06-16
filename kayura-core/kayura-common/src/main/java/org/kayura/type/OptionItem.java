/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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
