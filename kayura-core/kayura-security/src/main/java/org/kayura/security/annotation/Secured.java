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
