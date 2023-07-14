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

package org.kayura.expression;

import org.kayura.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class ConditionExpression {

  private Logical logic = Logical.AND;
  private String leftValue;
  private String leftName;
  private ValueType type = ValueType.Text;
  private Operator operator = Operator.Eq;
  private Object rightValue;
  private String rightName;
  private List<ConditionExpression> nestedExpressions = new ArrayList<>();

  public static ConditionExpression create() {
    return new ConditionExpression();
  }

  // --- 构建可执行SQL脚本 ---

  public void appendSqlExpression(int index, StringBuilder sqlExpression) {

    if (index > 0) {
      sqlExpression.append(" ").append(this.logic.getValue()).append(" ");
    }
    sqlExpression.append("(");

    // 先检查当前表达式是否为一个嵌套的表达式
    if (CollectionUtils.isNotEmpty(this.nestedExpressions)) {
      for (int i = 0; i < nestedExpressions.size(); i++) {
        nestedExpressions.get(i).appendSqlExpression(i, sqlExpression);
      }
    } else {
      // 构建当前条件表达式的SQL子表达式
      sqlExpression.append(leftValue).append(" ").append(operator);
      if (this.valueIsExpr(rightValue) || this.type.equals(ValueType.Number)) {
        sqlExpression.append(" ").append(rightValue);
      } else {
        sqlExpression.append(" '").append(rightValue).append("'");
      }
    }

    sqlExpression.append(")");
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

  public Object getRightValue() {
    return rightValue;
  }

  public ConditionExpression setRightValue(Object rightValue) {
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
