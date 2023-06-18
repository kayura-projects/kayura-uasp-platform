/*------------------------------------------------------------------------------
 - 版权所有 (c) 2023 Kayura
 -
 - 根据 Apache 许可证版本 2.0（"许可证"）获得许可；
 - 除非遵守许可，否则您不得使用此文件。
 - 您可以在以下网址获取许可的副本：
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - 除非适用法律要求或书面同意，根据许可分发的软件以“原样”分发，
 - 不附带任何明示或暗示的保证或条件。
 - 请参阅许可证以了解管理权限的特定语言和限制。
 -
 - 联系人：liangxia@live.com
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
