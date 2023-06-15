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

package org.kayura.utils.annotation;

import java.lang.annotation.*;

/**
 * 用来覆盖指定元注解中的指定属性（即覆盖注解方法值）
 *
 * @author meilin.huang
 * @version 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface OverrideFor {

  String value() default "";

  /**
   * 别名的属性名称(即别名的方法名)
   * 为空则为作用的注解方法元素的方法名
   */
  String attribute() default "";

  /**
   * 声明要覆盖的目标注解的别名属性的注解类型<br/>
   * 即attribute属性的声明类
   */
  Class<? extends Annotation> annotation();
}