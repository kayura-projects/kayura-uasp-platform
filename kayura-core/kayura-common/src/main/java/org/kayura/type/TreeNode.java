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

import java.util.ArrayList;
import java.util.List;

/**
 * 表示树型数据结构类型。
 */
public class TreeNode {

  private String id;
  private String code;
  private String text;
  private String type;
  private Boolean checked;
  private List<TreeNode> children;
  private Integer depth;
  private Object data;
  private String icon;
  private String styleClass;
  private Properties attr;
  private String hint;

  public static TreeNode create() {
    return new TreeNode();
  }

  public TreeNode addChildren(TreeNode treeNode) {
    if (this.children == null) {
      this.children = new ArrayList<>();
    }
    if (this.depth != null) {
      treeNode.depth = this.depth + 1;
    }
    this.children.add(treeNode);
    return this;
  }

  public TreeNode addAttr(String key, Object value) {
    if (this.attr == null) {
      this.attr = new Properties();
    }
    this.attr.put(key, value);
    return this;
  }

  public String getId() {
    return id;
  }

  public TreeNode setId(String id) {
    this.id = id;
    return this;
  }

  public String getCode() {
    return code;
  }

  public TreeNode setCode(String code) {
    this.code = code;
    return this;
  }

  public String getText() {
    return text;
  }

  public TreeNode setText(String text) {
    this.text = text;
    return this;
  }

  public String getType() {
    return type;
  }

  public TreeNode setType(String type) {
    this.type = type;
    return this;
  }

  public Boolean getChecked() {
    return checked;
  }

  public TreeNode setChecked(Boolean checked) {
    this.checked = checked;
    return this;
  }

  public List<TreeNode> getChildren() {
    return children;
  }

  public TreeNode setChildren(List<TreeNode> children) {
    this.children = children;
    return this;
  }

  public Integer getDepth() {
    return depth;
  }

  public TreeNode setDepth(Integer depth) {
    this.depth = depth;
    return this;
  }

  public Object getData() {
    return data;
  }

  public TreeNode setData(Object data) {
    this.data = data;
    return this;
  }

  public String getIcon() {
    return icon;
  }

  public TreeNode setIcon(String icon) {
    this.icon = icon;
    return this;
  }

  public String getStyleClass() {
    return styleClass;
  }

  public TreeNode setStyleClass(String styleClass) {
    this.styleClass = styleClass;
    return this;
  }

  public Properties getAttr() {
    return attr;
  }

  public TreeNode setAttr(Properties attr) {
    this.attr = attr;
    return this;
  }

  public String getHint() {
    return hint;
  }

  public TreeNode setHint(String hint) {
    this.hint = hint;
    return this;
  }

}
