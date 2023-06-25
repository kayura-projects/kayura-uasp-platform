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
