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

package org.kayura.mybatis.mapper;

import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper<ENTITY> extends BaseMapper<ENTITY> {

  List<ENTITY> selectList(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  int selectCount(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  ENTITY selectOne(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  PageList<ENTITY> selectPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

  PageList<Map<String, Object>> selectMapsPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

}
