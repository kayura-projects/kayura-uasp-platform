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

package org.kayura.mybatis.manager.Chain;

import org.kayura.mybatis.mapper.SelectMapper;
import org.kayura.mybatis.mapper.wrapper.*;
import org.kayura.mybatis.mapper.wrapper.segments.NormalSegmentList;
import org.kayura.mybatis.mapper.wrapper.support.Getter;

public class LambdaQueryChainWrapper<T> extends AbstractQueryWrapper<Getter<T>> implements ChainQuery<T> {

  protected SelectMapper<T> baseMapper;

  public LambdaQueryChainWrapper(SelectMapper<T> baseMapper, Class<?> entityClass) {
    super(entityClass);
    this.baseMapper = baseMapper;
  }

  @Override
  protected AbstractWhereWrapper<Getter<T>> nestedInstance() {
    return new LambdaWhereWrapper<>(this.entityClass, paramSeq, paramValues, new NormalSegmentList());
  }

  @Override
  public SelectMapper<T> getBaseMapper() {
    return baseMapper;
  }

  @Override
  public Wrapper getWrapper() {
    return this;
  }
}
