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
