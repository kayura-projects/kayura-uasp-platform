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

