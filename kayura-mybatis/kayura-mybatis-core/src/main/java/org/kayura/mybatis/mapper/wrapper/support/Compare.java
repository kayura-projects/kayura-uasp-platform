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
