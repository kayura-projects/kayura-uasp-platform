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

import org.kayura.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConditionExpression {

  private Logical logic = Logical.AND;
  private String leftValue;
  private String leftName;
  private ValueType type = ValueType.Text;
  private Operator operator = Operator.Eq;
  private String rightValue;
  private String rightName;
  private List<ConditionExpression> nestedExpressions = new ArrayList<>();

  public static ConditionExpression create() {
    return new ConditionExpression();
  }

  // --- 构建可执行SQL脚本 ---

  public void appendSqlExpression(Map<String, Object> params, int index, StringBuilder sqlExpression) {

    if (index > 0) {
      sqlExpression.append(" ").append(this.logic.getValue()).append(" ");
    }
    sqlExpression.append("(");

    if (CollectionUtils.isNotEmpty(this.nestedExpressions)) {
      for (int i = 0; i < nestedExpressions.size(); i++) {
        nestedExpressions.get(i).appendSqlExpression(params, i, sqlExpression);
      }
    } else {
      sqlExpression.append(leftValue).append(" ").append(operator).append(" ");
      if (this.valueIsExpr(rightValue)) {
        Object target = params.get(rightValue);
        if (target instanceof String) {
          sqlExpression.append(this.escapeString((String) target));
        } else if (target instanceof Number) {
          sqlExpression.append(target);
        } else if (target instanceof SubqueryExpression subqueryExpression) {
          sqlExpression.append("(").append(subqueryExpression.buildSql(params)).append(")");
        }
      } else if (this.type.equals(ValueType.Number)) {
        sqlExpression.append(rightValue);
      } else {
        sqlExpression.append(this.escapeString(rightValue));
      }
    }
    sqlExpression.append(")");
  }

  private String escapeString(String input) {
    if (input == null) {
      return "IS NULL";
    }
    String escapedString = input.replace("'", "''");
    return "'" + escapedString + "'";
  }

  private boolean valueIsExpr(Object value) {
    return value instanceof String && ((String) value).startsWith("${") && ((String) value).endsWith("}");
  }

  public Logical getLogic() {
    return logic;
  }

  public ConditionExpression setLogic(Logical logic) {
    this.logic = logic;
    return this;
  }

  public String getLeftValue() {
    return leftValue;
  }

  public ConditionExpression setLeftValue(String leftValue) {
    this.leftValue = leftValue;
    return this;
  }

  public String getLeftName() {
    return leftName;
  }

  public ConditionExpression setLeftName(String leftName) {
    this.leftName = leftName;
    return this;
  }

  public ValueType getType() {
    return type;
  }

  public ConditionExpression setType(ValueType type) {
    this.type = type;
    return this;
  }

  public Operator getOperator() {
    return operator;
  }

  public ConditionExpression setOperator(Operator operator) {
    this.operator = operator;
    return this;
  }

  public String getRightValue() {
    return rightValue;
  }

  public ConditionExpression setRightValue(String rightValue) {
    this.rightValue = rightValue;
    return this;
  }

  public String getRightName() {
    return rightName;
  }

  public ConditionExpression setRightName(String rightName) {
    this.rightName = rightName;
    return this;
  }

  public List<ConditionExpression> getNestedExpressions() {
    return nestedExpressions;
  }

  public ConditionExpression setNestedExpressions(List<ConditionExpression> nestedExpressions) {
    this.nestedExpressions = nestedExpressions;
    return this;
  }

  public ConditionExpression addSubExpression(ConditionExpression subExpression) {
    this.nestedExpressions.add(subExpression);
    return this;
  }
}
