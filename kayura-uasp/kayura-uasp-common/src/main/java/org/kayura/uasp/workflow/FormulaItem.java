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

package org.kayura.uasp.workflow;

public class FormulaItem {

  private String lo;
  private String name;
  private String type;
  private String ro;
  private String value;

  public String getLo() {
    return lo;
  }

  public FormulaItem setLo(String lo) {
    this.lo = lo;
    return this;
  }

  public String getName() {
    return name;
  }

  public FormulaItem setName(String name) {
    this.name = name;
    return this;
  }

  public String getType() {
    return type;
  }

  public FormulaItem setType(String type) {
    this.type = type;
    return this;
  }

  public String getRo() {
    return ro;
  }

  public FormulaItem setRo(String ro) {
    this.ro = ro;
    return this;
  }

  public String getValue() {
    return value;
  }

  public FormulaItem setValue(String value) {
    this.value = value;
    return this;
  }
}
