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

package org.kayura.mybatis.manager;

import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.manager.Chain.LambdaQueryChainWrapper;
import org.kayura.mybatis.manager.Chain.QueryChainWrapper;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageClause;
import org.kayura.type.PageList;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface SelectManager<T> extends BaseManager {

  QueryChainWrapper<T> queryWrapper();

  LambdaQueryChainWrapper<T> lambdaQueryWrapper();

  T selectOne(Wrapper queryWrapper);

  T selectOne(Consumer<LambdaQueryChainWrapper<T>> consumer);

  int selectCount(Wrapper queryWrapper);

  int selectCount(Consumer<LambdaQueryChainWrapper<T>> consumer);

  List<T> selectList(Wrapper queryWrapper);

  List<T> selectList(Consumer<LambdaQueryChainWrapper<T>> consumer);

  List<Map<String, Object>> selectMaps(Wrapper queryWrapper);

  List<Map<String, Object>> selectMaps(Consumer<LambdaQueryChainWrapper<T>> consumer);

  PageList<T> selectPage(Wrapper queryWrapper, PageBounds pageBounds);

  PageList<T> selectPage(Consumer<LambdaQueryChainWrapper<T>> consumer, PageClause pageClause);

  PageList<Map<String, Object>> selectMapsPage(Wrapper queryWrapper, PageBounds pageBounds);

  PageList<Map<String, Object>> selectMapsPage(Consumer<LambdaQueryChainWrapper<T>> consumer, PageBounds pageBounds);

}
