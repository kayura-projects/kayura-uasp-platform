/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 ~ 版权所属 2019 Liang.Xia 及 原作者
 ~ 您可以在 Apache License 2.0 版下获得许可副本，
 ~ 同时必须保证分发的本软件是在“原始”的基础上分发的。
 ~ 除非适用法律要求或书面同意。
 ~
 ~ http://www.apache.org/licenses/LICENSE-2.0
 ~
 ~ 请参阅许可证中控制权限和限制的特定语言。
 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

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
