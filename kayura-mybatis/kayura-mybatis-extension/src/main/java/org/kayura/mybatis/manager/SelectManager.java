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
