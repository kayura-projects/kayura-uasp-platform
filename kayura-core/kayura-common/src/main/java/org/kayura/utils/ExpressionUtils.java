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

package org.kayura.utils;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Map;

public class ExpressionUtils {

  private static class SingletonHolder {
    private static final ExpressionParser INSTANCE = new SpelExpressionParser();
  }

  public static ExpressionParser getInstance() {
    return SingletonHolder.INSTANCE;
  }

  public static boolean hasTrue(String expression, Map<String, Object> variables) {

    EvaluationContext context = new StandardEvaluationContext();
    if (variables != null) {
      for (Map.Entry<String, Object> var : variables.entrySet()) {
        context.setVariable(var.getKey(), var.getValue());
      }
    }
    Boolean result = getInstance().parseExpression(expression).getValue(context, Boolean.class);
    return Boolean.TRUE.equals(result);
  }

}

