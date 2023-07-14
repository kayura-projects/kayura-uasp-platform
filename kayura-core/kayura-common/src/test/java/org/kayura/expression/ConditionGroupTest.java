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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConditionGroupTest {

  @Test
  void simpleTest1() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("price").setType(ValueType.Number).setRightValue(1.0)
      );
    String string = group.buildSql().toString();
    Assertions.assertEquals(string, "(price = 1.0)");
  }

  @Test
  void simpleTest2() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("userId").setType(ValueType.Text).setRightValue("10000")
      );
    String string = group.buildSql().toString();
    Assertions.assertEquals(string, "(userId = '10000')");
  }

  @Test
  void dateTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("createDate").setType(ValueType.Date).setRightValue("2023-04-04")
      );
    String string = group.buildSql().toString();
    Assertions.assertEquals(string, "(createDate = '2023-04-04')");
  }

  @Test
  void valueExprTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("userId").setRightValue("${userId}")
      );
    String string = group.buildSql().toString();
    Assertions.assertEquals(string, "(userId = ${userId})");
  }

  @Test
  void nestedExprTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("userId").setRightValue("${userId}")
      ).addCondition(
        ConditionExpression.create()
          .setLogic(Logical.OR)
          .addSubExpression(
            ConditionExpression.create().setLeftValue("amount").setType(ValueType.Number).setOperator(Operator.GtEq).setRightValue(10000)
          ).addSubExpression(
            ConditionExpression.create().setLeftValue("amount").setType(ValueType.Number).setOperator(Operator.Lt).setRightValue(50000)
          )
      );
    String string = group.buildSql().toString();
    Assertions.assertEquals(string, "(userId = ${userId}) OR ((amount >= 10000) AND (amount < 50000))");
  }
}