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
