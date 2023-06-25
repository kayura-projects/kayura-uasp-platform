/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper.injector;

import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.toolkit.StringKit;

public class SqlTagTool implements Constants {

  public static String ifTag(final String ifTest, final String sqlScript) {
    return StringKit.format("<if test=\"%s\">%s</if>", ifTest, sqlScript);
  }

  public static String trimTag(final String sqlScript, final String prefix, final String suffix,
                               final String prefixOverrides, final String suffixOverrides) {
    StringBuilder sb = new StringBuilder("<trim");
    if (StringKit.isNotEmpty(prefix)) {
      sb.append(" prefix=\"").append(prefix).append(QUOTE);
    }
    if (StringKit.isNotEmpty(suffix)) {
      sb.append(" suffix=\"").append(suffix).append(QUOTE);
    }
    if (StringKit.isNotEmpty(prefixOverrides)) {
      sb.append(" prefixOverrides=\"").append(prefixOverrides).append(QUOTE);
    }
    if (StringKit.isNotEmpty(suffixOverrides)) {
      sb.append(" suffixOverrides=\"").append(suffixOverrides).append(QUOTE);
    }
    return sb.append(RIGHT_CHEV).append(sqlScript).append("</trim>").toString();
  }

  public static String chooseTag(final String whenTest, final String whenSqlScript, final String otherwise) {
    return "<choose>"
      + "<when test=\"" + whenTest + "\">" + whenSqlScript + "</when>"
      + "<otherwise>" + otherwise + "</otherwise>"
      + "</choose>";
  }

  public static String safeParam(final String param) {
    return HASH_LEFT_BRACE + param + RIGHT_BRACE;
  }

  public static String unSafeParam(final String param) {
    return DOLLAR_LEFT_BRACE + param + RIGHT_BRACE;
  }

  public static String whereTag(final String sqlScript) {
    return "<where>" + sqlScript + "</where>";
  }

  public static String setTag(final String sqlScript) {
    return "<set>" + sqlScript + "</set>";
  }

  public static String foreachTag(final String sqlScript, final String collection, final String index,
                                  final String item, final String separator) {

    StringBuilder sb = new StringBuilder("<foreach");
    if (StringKit.isNotEmpty(collection)) {
      sb.append(" collection=\"").append(collection).append(QUOTE);
    }
    if (StringKit.isNotEmpty(index)) {
      sb.append(" index=\"").append(index).append(QUOTE);
    }
    if (StringKit.isNotEmpty(item)) {
      sb.append(" item=\"").append(item).append(QUOTE);
    }
    if (StringKit.isNotEmpty(separator)) {
      sb.append(" separator=\"").append(separator).append(QUOTE);
    }
    return sb.append(RIGHT_CHEV).append(sqlScript).append("</foreach>").toString();
  }

}
