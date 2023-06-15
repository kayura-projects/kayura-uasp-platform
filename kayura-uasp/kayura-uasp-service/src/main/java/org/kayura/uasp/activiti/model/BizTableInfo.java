package org.kayura.uasp.activiti.model;

public class BizTableInfo {

  private String tableName;
  private String idField;
  private String statusField;
  private String flowField;

  public static BizTableInfo create() {
    return new BizTableInfo();
  }

  public String getTableName() {
    return tableName;
  }

  public BizTableInfo setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getIdField() {
    return idField;
  }

  public BizTableInfo setIdField(String idField) {
    this.idField = idField;
    return this;
  }

  public String getStatusField() {
    return statusField;
  }

  public BizTableInfo setStatusField(String statusField) {
    this.statusField = statusField;
    return this;
  }

  public String getFlowField() {
    return flowField;
  }

  public BizTableInfo setFlowField(String flowField) {
    this.flowField = flowField;
    return this;
  }
}
