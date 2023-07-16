/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
 -----------------------------------------------------------------------------*/

package org.kayura.security.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SubqueryExpression {

  private String tableName;
  private String tableAlias;
  private String fieldName;
  private boolean distinct;
  private List<ConditionExpression> conditions = new ArrayList<>();

  public static SubqueryExpression create() {
    return new SubqueryExpression();
  }

  public StringBuilder buildSql(Map<String, Object> params) {

    StringBuilder builder = new StringBuilder();
    builder.append("SELECT ").append(this.fieldName).append(" FROM ");
    builder.append(this.tableName).append(" ").append(this.tableAlias);
    if (!conditions.isEmpty()) {
      builder.append(" WHERE ");
      for (int i = 0; i < conditions.size(); i++) {
        conditions.get(i).appendSqlExpression(params, i, builder);
      }
    }
    return builder;
  }

  public SubqueryExpression addCondition(ConditionExpression subExpression) {
    this.conditions.add(subExpression);
    return this;
  }

  public String getTableName() {
    return tableName;
  }

  public SubqueryExpression setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getTableAlias() {
    return tableAlias;
  }

  public SubqueryExpression setTableAlias(String tableAlias) {
    this.tableAlias = tableAlias;
    return this;
  }

  public String getFieldName() {
    return fieldName;
  }

  public SubqueryExpression setFieldName(String fieldName) {
    this.fieldName = fieldName;
    return this;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public SubqueryExpression setDistinct(boolean distinct) {
    this.distinct = distinct;
    return this;
  }

  public List<ConditionExpression> getConditions() {
    return conditions;
  }

  public SubqueryExpression setConditions(List<ConditionExpression> conditions) {
    this.conditions = conditions;
    return this;
  }

}
