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