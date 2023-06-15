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

package org.kayura.mybatis.mapper.wrapper.support;

import java.io.Serializable;
import java.util.function.Function;

public interface Nested<Children, Param> extends Serializable {

  /**
   * and 嵌套查询
   *
   * @param condition
   * @param func
   * @return
   */
  Children and(boolean condition, Function<Param, Param> func);

  default Children and(Function<Param, Param> func) {
    return and(true, func);
  }

  /**
   * or 嵌套查询
   *
   * @param condition
   * @param func
   * @return
   */
  Children or(boolean condition, Function<Param, Param> func);

  default Children or(Function<Param, Param> func) {
    return or(true, func);
  }

  /**
   * 不使用 and 或者 or 的嵌套查询
   *
   * @param condition
   * @param func
   * @return
   */
  Children nested(boolean condition, Function<Param, Param> func);

  default Children nested(Function<Param, Param> func) {
    return nested(true, func);
  }

}
