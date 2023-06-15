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
