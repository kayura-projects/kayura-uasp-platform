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
