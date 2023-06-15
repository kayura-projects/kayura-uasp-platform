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
