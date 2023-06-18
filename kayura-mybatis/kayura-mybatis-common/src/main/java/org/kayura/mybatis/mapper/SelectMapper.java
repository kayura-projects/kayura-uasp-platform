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

package org.kayura.mybatis.mapper;

import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper<ENTITY> extends BaseMapper {

  List<ENTITY> selectList(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  int selectCount(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  ENTITY selectOne(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  PageList<ENTITY> selectPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

  PageList<Map<String, Object>> selectMapsPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

}
