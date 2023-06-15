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
