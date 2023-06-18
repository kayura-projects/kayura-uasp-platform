/*------------------------------------------------------------------------------
 - Copyright 2023 Kayura ( liangxia@live.com )
 -
 - Licensed under the Apache License, Version 2.0 (the "License");
 - you may not use this file except in compliance with the License.
 - You may obtain a copy of the License at
 -
 -     http://www.apache.org/licenses/LICENSE-2.0
 -
 - Unless required by applicable law or agreed to in writing, software
 - distributed under the License is distributed on an "AS IS" BASIS,
 - WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 - See the License for the specific language governing permissions and
 - limitations under the License.
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
