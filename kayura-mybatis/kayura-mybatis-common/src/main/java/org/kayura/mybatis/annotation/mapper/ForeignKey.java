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
