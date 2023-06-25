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

public class ExtendField {

  private String key;
  private String value;
  private String remark;

  public static ExtendField create() {
    return new ExtendField();
  }

  public static ExtendField clone(ExtendField source) {
    return create().setKey(source.key).setValue(source.value).setRemark(source.remark);
  }

  public String getKey() {
    return key;
  }

  public ExtendField setKey(String key) {
    this.key = key;
    return this;
  }

  public String getValue() {
    return value;
  }

  public ExtendField setValue(String value) {
    this.value = value;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public ExtendField setRemark(String remark) {
    this.remark = remark;
    return this;
  }

}
