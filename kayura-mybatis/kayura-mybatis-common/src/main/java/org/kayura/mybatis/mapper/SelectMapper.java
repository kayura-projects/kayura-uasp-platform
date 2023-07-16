/*------------------------------------------------------------------------------
 - 版权所有 (C) 2023 kayura
 -
 - 本程序是一个开源软件，根据 GNU 通用公共许可证 (AGPLv3) 的条款发布。
 - 您可以按照该许可证的规定重新发布和修改本程序。
 -
 - 有关许可证的详细信息，请参阅 LICENSE.md 文件。
 - 如果您有任何问题、建议或贡献，请联系版权所有者：<liangxia@live.com>
 -
 - 本程序基于无任何明示或暗示的担保提供，包括但不限于适销性和特定用途适用性的担保。
 - 请参阅 GNU 通用公共许可证以获取详细信息。
 -----------------------------------------------------------------------------*/

package org.kayura.mybatis.mapper;

import org.kayura.data.Mapper;
import org.kayura.mybatis.mapper.wrapper.Wrapper;
import org.kayura.mybatis.toolkit.Constants;
import org.kayura.mybatis.type.PageBounds;
import org.kayura.type.PageList;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SelectMapper<ENTITY> extends Mapper {

  List<ENTITY> selectList(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  int selectCount(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  ENTITY selectOne(@Param(Constants.WRAPPER) Wrapper queryWrapper);

  PageList<ENTITY> selectPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

  PageList<Map<String, Object>> selectMapsPage(@Param(Constants.WRAPPER) Wrapper queryWrapper, PageBounds pageBounds);

}
