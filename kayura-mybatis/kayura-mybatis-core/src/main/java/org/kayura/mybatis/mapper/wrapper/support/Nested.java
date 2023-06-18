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
