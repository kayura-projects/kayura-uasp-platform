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
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;

import static java.util.stream.Collectors.toList;

/**
 * 查询条件定义。
 *
 * @param <Children>
 * @param <R>
 */
public interface Compare<Children, R> extends Serializable {

  <V> Children allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull);

  <V> Children allEq(boolean condition, Map<R, V> params, boolean null2IsNull);

  default <V> Children allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull) {
    return allEq(true, filter, params, null2IsNull);
  }

  default <V> Children allEq(Map<R, V> params) {
    return allEq(params, true);
  }

  default <V> Children allEq(BiPredicate<R, V> filter, Map<R, V> params) {
    return allEq(filter, params, true);
  }

  default <V> Children allEq(Map<R, V> params, boolean null2Isnull) {
    return allEq(true, params, null2Isnull);
  }


  Children eq(boolean condition, R column, Object value);

  default Children eq(R column, Object value) {
    return eq(true, column, value);
  }

  Children notEq(boolean condition, R column, Object value);

  default Children notEq(R column, Object value) {
    return notEq(true, column, value);
  }


  Children gt(boolean condition, R column, Object value);

  default Children gt(R column, Object value) {
    return gt(true, column, value);
  }

  Children gtEq(boolean condition, R column, Object value);

  default Children gtEq(R column, Object value) {
    return gtEq(true, column, value);
  }


  Children lt(boolean condition, R column, Object value);

  default Children lt(R column, Object value) {
    return lt(true, column, value);
  }

  Children ltEq(boolean condition, R column, Object value);

  default Children ltEq(R column, Object value) {
    return ltEq(true, column, value);
  }


  Children between(boolean condition, R column, Object value1, Object value2);

  default Children between(R column, Object value1, Object value2) {
    return between(true, column, value1, value2);
  }

  Children notBetween(boolean condition, R column, Object value1, Object value2);

  default Children notBetween(R column, Object value1, Object value2) {
    return notBetween(true, column, value1, value2);
  }


  Children like(boolean condition, R column, Object value);

  default Children like(R column, Object value) {
    return like(true, column, value);
  }

  Children notLike(boolean condition, R column, Object value);

  default Children notLike(R column, Object value) {
    return notLike(true, column, value);
  }

  Children likeLeft(boolean condition, R column, Object value);

  default Children likeLeft(R column, Object value) {
    return likeLeft(true, column, value);
  }

  Children likeRight(boolean condition, R column, Object value);

  default Children likeRight(R column, Object value) {
    return likeRight(true, column, value);
  }


  Children isNull(boolean condition, R column);

  default Children isNull(R column) {
    return isNull(true, column);
  }

  Children isNotNull(boolean condition, R column);

  default Children isNotNull(R column) {
    return isNotNull(true, column);
  }


  Children in(boolean condition, R column, Collection<?> values);

  Children inSql(boolean condition, R column, String inValue);

  default Children in(R column, Collection<?> values) {
    return in(true, column, values);
  }

  default Children in(boolean condition, R column, Object... values) {
    return in(true, column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{})).collect(toList()));
  }

  default Children in(R column, Object... values) {
    return in(true, column, values);
  }

  default Children inSql(R column, String inValue) {
    return inSql(true, column, inValue);
  }


  Children notIn(boolean condition, R column, Collection<?> values);

  Children notInSql(boolean condition, R column, String inValue);

  default Children notIn(R column, Collection<?> values) {
    return notIn(true, column, values);
  }

  default Children notIn(boolean condition, R column, Object... values) {
    return notIn(true, column, Arrays.stream(Optional.ofNullable(values).orElseGet(() -> new Object[]{})).collect(toList()));
  }

  default Children notIn(R column, Object... values) {
    return notIn(true, column, values);
  }

  default Children notInSql(R column, String inValue) {
    return notInSql(true, column, inValue);
  }

}
