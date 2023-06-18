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
