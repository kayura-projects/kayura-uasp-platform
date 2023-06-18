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

package org.kayura.mybatis.annotation.mapper;

import java.lang.annotation.*;

/**
 * 外键映射注解。
 *
 * @author liangXia@live.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(ForeignKeyList.class)
public @interface ForeignKey {

  /** 外键物理表名 */
  String table() default "";

  /** 外键实体名，将从此实体解析出表名 */
  Class<?> entity() default Object.class;

  /** 设置该外键表的别名 */
  String alias();

  /** 外键表的主键字段名，若设置实体名，将可从@Id注解字段中获取。 */
  String pkName() default "";

  /** 可指定的附加关联条件 */
  String condition() default "";

  /** 指定表的联连方式 */
  JoinTypes joinType() default JoinTypes.LEFT;

}
