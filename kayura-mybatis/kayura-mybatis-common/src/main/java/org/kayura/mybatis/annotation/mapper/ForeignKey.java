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
