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
