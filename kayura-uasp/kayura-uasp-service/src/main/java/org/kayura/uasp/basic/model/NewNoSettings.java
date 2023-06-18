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

package org.kayura.uasp.basic.model;

public class NewNoSettings {

  private String defineId;
  private String countId;
  private Integer incValue;
  private Integer countValue;
  private Integer countLength;
  private String customCycle;
  private String expression;

  public static NewNoSettings create() {
    return new NewNoSettings();
  }

  public Integer getIncValue() {
    return incValue;
  }

  public NewNoSettings setIncValue(Integer incValue) {
    this.incValue = incValue;
    return this;
  }

  public Integer getCountValue() {
    return countValue;
  }

  public NewNoSettings setCountValue(Integer countValue) {
    this.countValue = countValue;
    return this;
  }

  public String getExpression() {
    return expression;
  }

  public NewNoSettings setExpression(String expression) {
    this.expression = expression;
    return this;
  }

  public Integer getCountLength() {
    return countLength;
  }

  public NewNoSettings setCountLength(Integer countLength) {
    this.countLength = countLength;
    return this;
  }

  public String getCountId() {
    return countId;
  }

  public NewNoSettings setCountId(String countId) {
    this.countId = countId;
    return this;
  }

  public String getDefineId() {
    return defineId;
  }

  public NewNoSettings setDefineId(String defineId) {
    this.defineId = defineId;
    return this;
  }

  public String getCustomCycle() {
    return customCycle;
  }

  public NewNoSettings setCustomCycle(String customCycle) {
    this.customCycle = customCycle;
    return this;
  }
}
