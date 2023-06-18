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

package org.kayura.mybatis.annotation.querier;

import org.kayura.mybatis.mapper.segments.SqlKeyword;
import org.kayura.utils.annotation.OverrideFor;

import java.lang.annotation.*;

/**
 * 映射为 大于等于 >= 条件表达式
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Mapping(compare = SqlKeyword.GT)
public @interface Gt {
  @OverrideFor(annotation = Mapping.class)
  String value() default "";
}
