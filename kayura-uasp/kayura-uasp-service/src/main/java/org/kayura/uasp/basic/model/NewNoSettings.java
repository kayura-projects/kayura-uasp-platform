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
