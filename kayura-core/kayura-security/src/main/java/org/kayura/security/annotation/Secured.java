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

package org.kayura.security.annotation;

import java.lang.annotation.*;

/**
 * 权限访问控制器注解。
 *
 * @author 夏亮（liangxia@live.com）
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Inherited
@Repeatable(Secureds.class)
public @interface Secured {

  /**
   * 授权的模块及资源.
   * <pre>如果定义多个，表示使用相同动作限制。</pre>
   * <pre>不允许为空，否则忽略。</pre>
   * <pre>不区分大小写。</pre>
   */
  String[] resource() default {};

  /**
   * 授权的行为动作。
   * <pre>如果未定义动作，表示不限制。</pre>
   */
  String[] actions() default {};

  /**
   * 授权可访问的角色。
   */
  String[] roles() default {};
}
