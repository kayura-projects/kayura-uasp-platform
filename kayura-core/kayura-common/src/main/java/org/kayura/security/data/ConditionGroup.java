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

public class ConditionGroup {

  private List<ConditionExpression> conditions = new ArrayList<>();

  public static ConditionGroup create() {
    return new ConditionGroup();
  }

  public StringBuilder buildSql(Map<String, Object> params) {

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < conditions.size(); i++) {
      conditions.get(i).appendSqlExpression(params, i, builder);
    }
    return builder;
  }

  public List<ConditionExpression> getConditions() {
    return conditions;
  }

  public ConditionGroup setConditions(List<ConditionExpression> conditions) {
    this.conditions = conditions;
    return this;
  }

  public ConditionGroup addCondition(ConditionExpression condition) {
    this.conditions.add(condition);
    return this;
  }
}
