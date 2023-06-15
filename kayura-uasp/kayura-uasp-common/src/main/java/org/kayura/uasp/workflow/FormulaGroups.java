package org.kayura.uasp.workflow;

import org.kayura.utils.Assert;
import org.kayura.utils.ExpressionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormulaGroups extends ArrayList<FormulaGroup> {

  public static final String RO_EQ = "==";
  public static final String RO_NOT_EQ = "!=";
  public static final String RO_GT = ">";
  public static final String RO_GT_EQ = ">=";
  public static final String RO_LT = "<";
  public static final String RO_LT_EQ = "<=";

  public static final Map<String, String> RO_TEXT = new HashMap<>();
  public static final Map<String, String> RO_NUMBER = new HashMap<>();
  public static final Map<String, String> RO_DATE = new HashMap<>();

  public static final String LO_AND = "and";
  public static final String LO_OR = "or";

  public static final String TYPE_TEXT = "text";
  public static final String TYPE_NUMBER = "number";
  public static final String TYPE_DATE = "date";

  public static final Map<String, String> LO_NAMES = new HashMap<>();

  public static boolean hasTrue(FormulaGroups formulaGroups, Map<String, Object> variables) {
    return ExpressionUtils.hasTrue(parser(formulaGroups), variables);
  }

  private static String parser(FormulaGroups formulaGroups) {

    Assert.notNull(formulaGroups, "nodes is null");

    StringBuilder all = new StringBuilder();

    for (FormulaGroup group : formulaGroups) {
      if (all.length() > 0) {
        all.append(group.getLo());
      }

      StringBuilder sb = new StringBuilder();
      List<FormulaItem> items = group.getItems();
      for (FormulaItem item : items) {
        if (sb.length() > 0) {
          sb.append(" ").append(item.getLo()).append(" ");
        }
        sb.append("( #").append(item.getName())
          .append(" ").append(item.getRo()).append(" ")
          .append(getValueOfType(item.getType(), item.getValue()))
          .append(" )");
      }
      all.append(sb);
    }
    return all.toString();
  }

  private static String getValueOfType(String type, Object value) {

    if (TYPE_NUMBER.equals(type)) {
      return value.toString();
    }
    return "'" + value + "'";
  }

}
