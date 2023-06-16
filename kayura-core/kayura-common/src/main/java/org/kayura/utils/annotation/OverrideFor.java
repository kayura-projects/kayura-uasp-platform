/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 - 有关许可证的详细信息，请参阅 LICENSE 文件。
 -
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
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