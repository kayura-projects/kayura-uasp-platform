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

package org.kayura.mybatis.manager.Chain;

import org.kayura.mybatis.mapper.SelectMapper;
import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

import java.util.List;

public interface ChainQuery<T> {

  SelectMapper<T> getBaseMapper();

  Wrapper getWrapper();

  default List<T> list() {
    return getBaseMapper().selectList(getWrapper());
  }

  default T one() {
    return getBaseMapper().selectOne(getWrapper());
  }

  default Integer count() {
    return getBaseMapper().selectCount(getWrapper());
  }

  /**
   * 获取分页数据
   *
   * @return 分页数据
   */
  default PageList<T> pageList(PageBounds pageBounds) {
    return getBaseMapper().selectPage(getWrapper(), pageBounds);
  }

}
