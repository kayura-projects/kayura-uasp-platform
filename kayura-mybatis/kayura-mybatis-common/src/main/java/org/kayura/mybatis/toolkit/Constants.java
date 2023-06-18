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

package org.kayura.mybatis.toolkit;

public interface Constants extends StringPool {

  String WRAPPER = "ew";

  String ENTITY = "et";

  String ENTITY_DOT = "et" + DOT;

  String ROW_ID = "ROW_ID";

  String ROW_IDS = "ROW_IDS";

  String QUERIER_DOT = WRAPPER + DOT;

  String QUERIER_COLUMNS = QUERIER_DOT + "sqlSelect";

  String QUERIER_WHERE = QUERIER_DOT + "sqlWhere";

  String QUERIER_LOGIC_DELETE = QUERIER_DOT + "logicDelete";

  String QUERIER_DELETE_VALUE = QUERIER_DOT + "deletedValue";

  String FMT_AND_WHERE = "AND %s";

  String FMT_NOT_NULL = "%s != null";

  String FMT_NOT_NULL_TWO = "%s != null and %s != null";

  String WRAPPER_PARAM = "v";

  String WRAPPER_PARAM_FORMAT = "#{%s.paramValues.%s}";
}
