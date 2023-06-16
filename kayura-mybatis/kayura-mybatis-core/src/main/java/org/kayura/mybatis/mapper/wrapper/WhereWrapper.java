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

package org.kayura.mybatis.mapper.wrapper;

import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WhereWrapper extends AbstractWhereWrapper<String> {

  public static WhereWrapper create(Class<?> entityClass) {
    return new WhereWrapper(entityClass);
  }

  public static <T> LambdaWhereWrapper<T> createLambda(Class<?> entityClass) {
    return new LambdaWhereWrapper<>(entityClass);
  }

  public WhereWrapper(Class<?> entityClass) {
    super(entityClass);
  }

  public WhereWrapper(Class<?> entityClass, AtomicInteger paramSeq,
                      Map<String, Object> paramNameValuePairs, NormalSegmentList normal) {
    super(entityClass);
    this.paramSeq = paramSeq;
    this.paramValues = paramNameValuePairs;
    this.normal = normal;
  }

  public <T> LambdaWhereWrapper<T> lambda() {
    return new LambdaWhereWrapper<>(this.entityClass, this.paramSeq, this.paramValues, this.normal);
  }

  @Override
  protected AbstractWhereWrapper<String> nestedInstance() {
    return new WhereWrapper(this.entityClass, paramSeq, paramValues, new NormalSegmentList());
  }

}
