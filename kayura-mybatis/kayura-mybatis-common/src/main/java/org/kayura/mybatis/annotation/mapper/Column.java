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
