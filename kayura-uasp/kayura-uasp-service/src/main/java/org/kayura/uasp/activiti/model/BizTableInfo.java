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
