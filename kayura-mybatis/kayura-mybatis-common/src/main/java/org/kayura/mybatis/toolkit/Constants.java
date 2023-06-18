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
