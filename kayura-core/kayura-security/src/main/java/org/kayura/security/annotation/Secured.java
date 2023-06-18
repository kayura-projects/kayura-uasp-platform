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
