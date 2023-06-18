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

package org.kayura.mybatis.mapper.wrapper;

import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class UpdateWrapper extends AbstractUpdateWrapper<String> {

  public static UpdateWrapper create(Class<?> entityClass) {
    return new UpdateWrapper(entityClass);
  }

  public static <T> LambdaUpdateWrapper<T> createLambda(Class<?> entityClass) {
    return new LambdaUpdateWrapper<>(entityClass);
  }

  public UpdateWrapper(Class<?> entityClass) {
    super(entityClass);
  }

  public UpdateWrapper(Class<?> entityClass, AtomicInteger paramSeq,
                       Map<String, Object> paramNameValuePairs, NormalSegmentList normal) {
    super(entityClass);
    this.paramSeq = paramSeq;
    this.paramValues = paramNameValuePairs;
    this.normal = normal;
  }

  public <T> LambdaUpdateWrapper<T> lambda() {
    return new LambdaUpdateWrapper<>(this.entityClass, this.paramSeq, this.paramValues, this.normal);
  }

  @Override
  protected AbstractWhereWrapper<String> nestedInstance() {
    return new WhereWrapper(this.entityClass, paramSeq, paramValues, new NormalSegmentList());
  }

}
