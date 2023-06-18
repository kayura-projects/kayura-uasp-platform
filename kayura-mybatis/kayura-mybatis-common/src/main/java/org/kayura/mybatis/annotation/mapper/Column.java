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

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.UnknownTypeHandler;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

  String value() default "";

  String alias() default "";

  String owner() default "";

  String el() default "";

  String displayName() default "";

  String hint() default "";

  boolean unique() default false;

  SelectModes select() default SelectModes.ALL;

  boolean insert() default true;

  boolean update() default true;

  boolean nullable() default true;

  int min() default Integer.MIN_VALUE;

  int max() default Integer.MAX_VALUE;

  /**
   * 当为数字类型时，表示是否可以为零值。
   */
  boolean zero() default true;

  JdbcType jdbcType() default JdbcType.UNDEFINED;

  Class<? extends TypeHandler<?>> typeHandler() default UnknownTypeHandler.class;

}
