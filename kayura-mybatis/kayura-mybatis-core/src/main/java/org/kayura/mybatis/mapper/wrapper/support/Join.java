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

package org.kayura.mybatis.mapper.wrapper.support;

import java.io.Serializable;

public interface Join<Children> extends Serializable {

  Children or(boolean condition);

  default Children or() {
    return or(true);
  }

  Children apply(boolean condition, String applySql, Object... value);

  default Children apply(String applySql, Object... value) {
    return apply(true, applySql, value);
  }


  Children exists(boolean condition, String existsSql);

  default Children exists(String existsSql) {
    return exists(true, existsSql);
  }


  Children notExists(boolean condition, String notExistsSql);

  default Children notExists(String notExistsSql) {
    return notExists(true, notExistsSql);
  }

}
