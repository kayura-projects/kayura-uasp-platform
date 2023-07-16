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
import org.kayura.security.data.*;

import java.util.HashMap;
import java.util.Map;

class ConditionGroupTest {

  static Map<String, Object> buildParams = new HashMap<>();

  static {
    buildParams.put("${loginUserId}", "10000");
    buildParams.put("${mix:authContractIds}",
      SubqueryExpression.create()
        .setTableName("t_contract").setTableAlias("cc").setFieldName("cc.contract_id_")
        .addCondition(
          ConditionExpression.create().setLeftValue("cc.data_id_").setRightValue("${loginUserId}")
        )
    );
  }

  @Test
  void simpleTest1() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("price").setType(ValueType.Number).setRightValue("1.0")
      );
    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(price = 1.0)", string);
  }

  @Test
  void simpleTest2() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("creatorId").setType(ValueType.Text).setRightValue("10000")
      );
    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(creatorId = '10000')", string);
  }

  @Test
  void dateTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("createDate").setType(ValueType.Date).setRightValue("2023-04-04")
      );
    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(createDate = '2023-04-04')", string);
  }

  @Test
  void valueExprTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("creatorId").setRightValue("${loginUserId}")
      );
    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(creatorId = '10000')", string);
  }

  @Test
  void nestedExprTest() {

    ConditionGroup group = ConditionGroup.create()
      .addCondition(
        ConditionExpression.create()
          .setLeftValue("creatorId").setRightValue("${loginUserId}")
      ).addCondition(
        ConditionExpression.create()
          .setLogic(Logical.OR)
          .addSubExpression(
            ConditionExpression.create().setLeftValue("amount").setType(ValueType.Number).setOperator(Operator.GtEq).setRightValue("10000")
          ).addSubExpression(
            ConditionExpression.create().setLeftValue("amount").setType(ValueType.Number).setOperator(Operator.Lt).setRightValue("50000")
          )
      );
    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(creatorId = '10000') OR ((amount >= 10000) AND (amount < 50000))", string);
  }

  @Test
  void subqueryExprTest() {

    ConditionGroup group = ConditionGroup.create().addCondition(
      ConditionExpression.create().setLeftValue("mt.creator_id_").setRightValue("${loginUserId}")
    ).addCondition(
      ConditionExpression.create().setLogic(Logical.OR).setLeftValue("mt.contract_id_").setOperator(Operator.In).setRightValue("${mix:authContractIds}")
    );

    String string = group.buildSql(buildParams).toString();
    Assertions.assertEquals("(mt.creator_id_ = '10000') OR (mt.contract_id_ IN (SELECT cc.contract_id_ FROM t_contract cc WHERE (cc.data_id_ = '10000')))", string);
  }

}