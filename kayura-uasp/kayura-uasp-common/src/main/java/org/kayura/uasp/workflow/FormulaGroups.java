/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

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
