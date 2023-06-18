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

import java.lang.annotation.*;

/**
 * 外键映射注解。
 *
 * @author liangXia@live.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(ForeignKeyList.class)
public @interface ForeignKey {

  /** 外键物理表名 */
  String table() default "";

  /** 外键实体名，将从此实体解析出表名 */
  Class<?> entity() default Object.class;

  /** 设置该外键表的别名 */
  String alias();

  /** 外键表的主键字段名，若设置实体名，将可从@Id注解字段中获取。 */
  String pkName() default "";

  /** 可指定的附加关联条件 */
  String condition() default "";

  /** 指定表的联连方式 */
  JoinTypes joinType() default JoinTypes.LEFT;

}
